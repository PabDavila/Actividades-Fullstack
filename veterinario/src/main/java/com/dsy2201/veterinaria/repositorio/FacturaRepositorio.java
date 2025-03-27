package com.dsy2201.veterinaria.repositorio;

import com.dsy2201.veterinaria.clases.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
    List<Factura> buscaPorCliente(String cliente);
}
