package com.colmena.belleza.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "timestamp with time zone")
    private OffsetDateTime fecha = OffsetDateTime.now();

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(length = 100)
    private String categoria;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public OffsetDateTime getFecha() { return fecha; }
    public void setFecha(OffsetDateTime fecha) { this.fecha = fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
