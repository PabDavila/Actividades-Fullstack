package com.dsy2201.veterinaria.repositorio;

import com.dsy2201.veterinaria.clases.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {
}
