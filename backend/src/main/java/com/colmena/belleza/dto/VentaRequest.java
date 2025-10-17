package com.colmena.belleza.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VentaRequest {
    public Long idCliente; // nullable -> default -1
    public String metodoPago;
    @NotNull
    public List<Item> items;

    public static class Item {
        @NotNull public Long idProducto;
        @Min(1) public Integer cantidad;
    }
}
