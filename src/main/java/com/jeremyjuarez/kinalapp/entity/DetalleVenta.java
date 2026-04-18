package com.jeremyjuarez.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "detalle_venta")

public class DetalleVenta {
    @Id
    @Column (name = "codigo_detalle_venta")
    private String codigoDetalleVenta;
    @Column
    private int cantidad;
    @Column
    private double precioUnitario;
    @Column
    private double precioTotal;
    @Column
    private int estado;

    @ManyToOne
    @JoinColumn(name = "Productos_codigo_producto")
    private Productos producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ventas_codigo_venta")
    private Ventas venta;

    public DetalleVenta() {
    }

    public DetalleVenta(String codigoDetalleVenta, int cantidad, double precioUnitario, double precioTotal) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }



    public String getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(String codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
