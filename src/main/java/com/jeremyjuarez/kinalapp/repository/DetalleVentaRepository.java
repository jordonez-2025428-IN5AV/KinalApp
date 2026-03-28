package com.jeremyjuarez.kinalapp.repository;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, String>{
    List<DetalleVenta> findByEstado(int estado);
}
