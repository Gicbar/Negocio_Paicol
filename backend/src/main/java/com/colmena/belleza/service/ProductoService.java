package com.colmena.belleza.service;

import com.colmena.belleza.common.NotFoundException;
import com.colmena.belleza.dto.ProductoDTO;
import com.colmena.belleza.entity.Categoria;
import com.colmena.belleza.entity.Producto;
import com.colmena.belleza.entity.Proveedor;
import com.colmena.belleza.repository.CategoriaRepository;
import com.colmena.belleza.repository.ProductoRepository;
import com.colmena.belleza.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Producto> list() { return productoRepository.findAll(); }

    public Producto get(Integer id) { return productoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado")); }

    @Transactional
    public Producto create(ProductoDTO dto) { return saveFromDto(new Producto(), dto); }

    @Transactional
    public Producto update(Integer id, ProductoDTO dto) {
        Producto existing = get(id);
        return saveFromDto(existing, dto);
    }

    @Transactional
    public void delete(Integer id) { productoRepository.deleteById(id); }

    public Producto findByCodigoBarras(String codigo) {
        return productoRepository.findByCodigoBarras(codigo)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado por código de barras"));
    }

    private Producto saveFromDto(Producto p, ProductoDTO dto) {
        p.setNombre(dto.nombre);
        p.setDescripcion(dto.descripcion);
        p.setPrecio(dto.precio);
        p.setStock(dto.stock != null ? dto.stock : 0);
        p.setCodigoBarras(dto.codigoBarras);
        if (dto.idCategoria != null) {
            Categoria cat = categoriaRepository.findById(dto.idCategoria)
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
            p.setCategoria(cat);
        } else {
            p.setCategoria(null);
        }
        if (dto.idProveedor != null) {
            Proveedor prov = proveedorRepository.findById(dto.idProveedor)
                    .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"));
            p.setProveedor(prov);
        } else {
            p.setProveedor(null);
        }
        return productoRepository.save(p);
    }
}
