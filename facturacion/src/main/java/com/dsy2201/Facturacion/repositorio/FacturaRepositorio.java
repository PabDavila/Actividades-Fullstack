package com.dsy2201.Facturacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsy2201.Facturacion.clases.Factura;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
}
