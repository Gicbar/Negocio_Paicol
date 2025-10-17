package com.colmena.belleza.dto;

import java.math.BigDecimal;

public class ProductoDTO {
    public Long id;
    public String nombre;
    public String descripcion;
    public BigDecimal precio;
    public Integer stock;
    public String codigoBarras;
    public Long idCategoria;
    public Long idProveedor;
}
