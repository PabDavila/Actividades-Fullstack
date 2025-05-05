package com.dsy2201.Semana_9.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsy2201.Semana_9.clases.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
}
