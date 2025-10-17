package com.colmena.belleza.controller;

import com.colmena.belleza.dto.ProductoDTO;
import com.colmena.belleza.entity.Producto;
import com.colmena.belleza.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> list() { return productoService.list(); }

    @GetMapping("/{id}")
    public Producto get(@PathVariable Long id) { return productoService.get(id); }

    @PostMapping
    public Producto create(@RequestBody ProductoDTO dto) { return productoService.create(dto); }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody ProductoDTO dto) { return productoService.update(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { productoService.delete(id); }

    @GetMapping("/barcode/{codigo}")
    public Producto byBarcode(@PathVariable String codigo) { return productoService.findByCodigoBarras(codigo); }
}
