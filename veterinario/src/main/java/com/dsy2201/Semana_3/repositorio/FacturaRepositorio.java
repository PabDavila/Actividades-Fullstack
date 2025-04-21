package com.dsy2201.Semana_3.repositorio;

import com.dsy2201.Semana_3.clases.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
}
