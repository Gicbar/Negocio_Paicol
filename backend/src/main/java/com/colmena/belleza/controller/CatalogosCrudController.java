package com.colmena.belleza.controller;

import com.colmena.belleza.common.NotFoundException;
import com.colmena.belleza.entity.Categoria;
import com.colmena.belleza.entity.Cliente;
import com.colmena.belleza.entity.Proveedor;
import com.colmena.belleza.repository.CategoriaRepository;
import com.colmena.belleza.repository.ClienteRepository;
import com.colmena.belleza.repository.ProveedorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogosCrudController {
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final ClienteRepository clienteRepository;

    public CatalogosCrudController(CategoriaRepository categoriaRepository,
                                   ProveedorRepository proveedorRepository,
                                   ClienteRepository clienteRepository) {
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.clienteRepository = clienteRepository;
    }

    // Categorias
    @GetMapping("/categorias")
    public List<Categoria> catList() { return categoriaRepository.findAll(); }
    @PostMapping("/categorias")
    public Categoria catCreate(@RequestBody Categoria c) { return categoriaRepository.save(c); }
    @PutMapping("/categorias/{id}")
    public Categoria catUpdate(@PathVariable Integer id, @RequestBody Categoria c) {
        c.setId(id); return categoriaRepository.save(c);
    }
    @DeleteMapping("/categorias/{id}")
    public void catDelete(@PathVariable Integer id) { categoriaRepository.deleteById(id); }

    // Proveedores
    @GetMapping("/proveedores")
    public List<Proveedor> provList() { return proveedorRepository.findAll(); }
    @PostMapping("/proveedores")
    public Proveedor provCreate(@RequestBody Proveedor p) { return proveedorRepository.save(p); }
    @PutMapping("/proveedores/{id}")
    public Proveedor provUpdate(@PathVariable Integer id, @RequestBody Proveedor p) {
        p.setId(id); return proveedorRepository.save(p);
    }
    @DeleteMapping("/proveedores/{id}")
    public void provDelete(@PathVariable Integer id) { proveedorRepository.deleteById(id); }

    // Clientes
    @GetMapping("/clientes")
    public List<Cliente> cliList() { return clienteRepository.findAll(); }
    @PostMapping("/clientes")
    public Cliente cliCreate(@RequestBody Cliente c) { return clienteRepository.save(c); }
    @PutMapping("/clientes/{id}")
    public Cliente cliUpdate(@PathVariable Integer id, @RequestBody Cliente c) {
        c.setId(id); return clienteRepository.save(c);
    }
    @DeleteMapping("/clientes/{id}")
    public void cliDelete(@PathVariable Integer id) { clienteRepository.deleteById(id); }
}
