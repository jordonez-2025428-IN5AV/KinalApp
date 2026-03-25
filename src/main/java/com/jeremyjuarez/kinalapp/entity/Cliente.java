package com.jeremyjuarez.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Crea la clase como una entidad
@Table(name = "clientes") //Cambia el nombre a plural

public class Cliente {
    @Id //Crea la llave Primaria
    @Column (name = "dpi_cliente") //Asigna un nombre a la Tabla
    private String DPICliente;
    @Column
    private String nombreCliente;
    @Column
    private String apellidoCliente;
    @Column
    private String direccionCliente;
    private int estado;

    public Cliente() {
    }

    public Cliente(String DPICliente, String direccionCliente, int estado, String apellidoCliente, String nombreCliente) {
        this.DPICliente = DPICliente;
        this.direccionCliente = direccionCliente;
        this.estado = estado;
        this.apellidoCliente = apellidoCliente;
        this.nombreCliente = nombreCliente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDPICliente() {
        return DPICliente;
    }

    public void setDPICliente(String DPICliente) {
        this.DPICliente = DPICliente;
    }
}
