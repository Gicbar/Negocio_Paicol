package com.colmena.belleza.controller;

import com.colmena.belleza.entity.Gasto;
import com.colmena.belleza.entity.InventarioMovimiento;
import com.colmena.belleza.repository.GastoRepository;
import com.colmena.belleza.repository.InventarioMovimientoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GastoInventarioController {
    private final GastoRepository gastoRepository;
    private final InventarioMovimientoRepository inventarioMovimientoRepository;

    public GastoInventarioController(GastoRepository gastoRepository,
                                     InventarioMovimientoRepository inventarioMovimientoRepository) {
        this.gastoRepository = gastoRepository;
        this.inventarioMovimientoRepository = inventarioMovimientoRepository;
    }

    // Gastos
    @GetMapping("/gastos")
    public List<Gasto> gastosList() { return gastoRepository.findAll(); }
    @PostMapping("/gastos")
    public Gasto gastosCreate(@RequestBody Gasto g) { return gastoRepository.save(g); }
    @PutMapping("/gastos/{id}")
    public Gasto gastosUpdate(@PathVariable Long id, @RequestBody Gasto g) { g.setId(id); return gastoRepository.save(g); }
    @DeleteMapping("/gastos/{id}")
    public void gastosDelete(@PathVariable Long id) { gastoRepository.deleteById(id); }

    // Inventario movimientos
    @GetMapping("/inventario_movimientos")
    public List<InventarioMovimiento> invList() { return inventarioMovimientoRepository.findAll(); }
    @PostMapping("/inventario_movimientos")
    public InventarioMovimiento invCreate(@RequestBody InventarioMovimiento m) { return inventarioMovimientoRepository.save(m); }
    @PutMapping("/inventario_movimientos/{id}")
    public InventarioMovimiento invUpdate(@PathVariable Long id, @RequestBody InventarioMovimiento m) { m.setId(id); return inventarioMovimientoRepository.save(m); }
    @DeleteMapping("/inventario_movimientos/{id}")
    public void invDelete(@PathVariable Long id) { inventarioMovimientoRepository.deleteById(id); }
}
