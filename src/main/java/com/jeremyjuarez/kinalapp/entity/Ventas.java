package com.jeremyjuarez.kinalapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "ventas")

public class Ventas {
    @Id
    @Column (name = "codigo_venta")
    private String codigoVenta;
    @Column
    private String fechaVenta;
    @Column
    private double precioVenta;
    @Column
    private int estado;

    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;

    public Ventas() {
    }

    public Ventas(String codigoVenta, String fechaVenta, double precioVenta, int estado) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.precioVenta = precioVenta;
        this.estado = estado;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
