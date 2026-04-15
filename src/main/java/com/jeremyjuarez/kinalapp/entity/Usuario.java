package com.jeremyjuarez.kinalapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @Column (name = "codigo_usuario")
    private String codigoUsuario;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private Integer estado;
    @OneToMany(mappedBy = "usuario")
    private List<Ventas> ventas;

    public Usuario() {
    }

    public Usuario(String codigoUsuario, String username, String password, String email, int estado, String rol) {
        this.codigoUsuario = codigoUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.estado = estado;
        this.rol = rol;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
