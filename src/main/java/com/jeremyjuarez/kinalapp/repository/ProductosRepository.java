package com.jeremyjuarez.kinalapp.repository;

import com.jeremyjuarez.kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, String> {
    List<Productos> findByEstado(int estado);
}
