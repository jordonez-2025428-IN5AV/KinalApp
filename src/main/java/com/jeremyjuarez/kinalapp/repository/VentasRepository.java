package com.jeremyjuarez.kinalapp.repository;

import com.jeremyjuarez.kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, String> {
    List<Ventas> findByEstado(int estado);
}