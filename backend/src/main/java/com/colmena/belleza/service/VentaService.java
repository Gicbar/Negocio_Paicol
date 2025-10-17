package com.colmena.belleza.service;

import com.colmena.belleza.common.NotFoundException;
import com.colmena.belleza.dto.VentaRequest;
import com.colmena.belleza.entity.Cliente;
import com.colmena.belleza.entity.DetalleVenta;
import com.colmena.belleza.entity.Producto;
import com.colmena.belleza.entity.Venta;
import com.colmena.belleza.repository.ClienteRepository;
import com.colmena.belleza.repository.DetalleVentaRepository;
import com.colmena.belleza.repository.ProductoRepository;
import com.colmena.belleza.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public VentaService(VentaRepository ventaRepository,
                        DetalleVentaRepository detalleVentaRepository,
                        ProductoRepository productoRepository,
                        ClienteRepository clienteRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Venta procesarVenta(VentaRequest request) {
        Venta venta = new Venta();
        if (request.idCliente != null && request.idCliente >= 0) {
            Cliente cliente = clienteRepository.findById(request.idCliente)
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
            venta.setCliente(cliente);
        } else {
            venta.setCliente(null); // cliente -1 equivale a null en BD
        }
        venta.setMetodoPago(request.metodoPago);
        venta.setTotal(BigDecimal.ZERO);
        venta = ventaRepository.save(venta);

        BigDecimal total = BigDecimal.ZERO;
        for (VentaRequest.Item item : request.items) {
            Producto producto = productoRepository.findById(item.idProducto)
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            if (producto.getStock() < item.cantidad) {
                throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - item.cantidad);
            productoRepository.save(producto);

            DetalleVenta det = new DetalleVenta();
            det.setVenta(venta);
            det.setProducto(producto);
            det.setCantidad(item.cantidad);
            det.setPrecioUnitario(producto.getPrecio());
            detalleVentaRepository.save(det);

            total = total.add(producto.getPrecio().multiply(BigDecimal.valueOf(item.cantidad)));
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }
}
