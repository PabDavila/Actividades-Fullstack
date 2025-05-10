
package com.dsy2201.facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsy2201.facturacion.clases.Servicio;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {
}