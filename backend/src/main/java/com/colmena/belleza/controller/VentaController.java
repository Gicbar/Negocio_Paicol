package com.colmena.belleza.controller;

import com.colmena.belleza.dto.VentaRequest;
import com.colmena.belleza.entity.Venta;
import com.colmena.belleza.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) { this.ventaService = ventaService; }

    @PostMapping
    public Venta procesar(@Valid @RequestBody VentaRequest request) {
        return ventaService.procesarVenta(request);
    }
}
