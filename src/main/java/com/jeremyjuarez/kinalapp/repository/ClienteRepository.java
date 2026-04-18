package com.jeremyjuarez.kinalapp.repository;

import com.jeremyjuarez.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByEstado(int estado);
    List<Cliente> findByDPIClienteContainingIgnoreCaseOrNombreClienteContainingIgnoreCase(String dpi, String nombre);
}
