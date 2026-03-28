package com.jeremyjuarez.kinalapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "productos")

public class Productos {
    @Id
    @Column (name = "codigo_producto")
    private String codigoProducto;
    @Column
    private String nombreProducto;
    @Column
    private float precio;
    @Column
    private int stock;
    @Column
    private int estado;
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalles;

    public Productos() {
    }

    public Productos(String codigoProducto, String nombreProducto, float precio, int stock, int estado) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
