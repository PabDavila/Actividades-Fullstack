package com.dsy2201.veterinaria.servicio;

import com.dsy2201.veterinaria.DTO.FacturaDTO;
import com.dsy2201.veterinaria.excepciones.ResourceNotFoundException;
import com.dsy2201.veterinaria.clases.Factura;
import com.dsy2201.veterinaria.clases.Servicio;
import com.dsy2201.veterinaria.repositorio.FacturaRepositorio;
import com.dsy2201.veterinaria.repositorio.ServicioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServicio {
    
    private final FacturaRepositorio facturaRepositorio;
    private final ServicioRepositorio servicioRepositorio;

    public FacturaServicio(FacturaRepositorio facturaRepositorio, ServicioRepositorio servicioRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
        this.servicioRepositorio = servicioRepositorio;
    }

    public Factura generarFactura(FacturaDTO facturaDTO) {
        List<Servicio> servicios = servicioRepositorio.findAllById(facturaDTO.getServiciosIds());
        
        if (servicios.isEmpty()) {
            throw new ResourceNotFoundException("Servicios no encontrados.");
        }

        double total = servicios.stream().mapToDouble(Servicio::getCosto).sum();

        Factura factura = new Factura(null, facturaDTO.getCliente(), null, servicios, total, false);
        return facturaRepositorio.save(factura);
    }

    public Factura pagarFactura(Long id) {
        Factura factura = facturaRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada."));

        if (factura.isPagada()) {
            throw new IllegalStateException("La factura ya está pagada.");
        }

        factura.setPagada(true);
        return facturaRepositorio.save(factura);
    }

    public List<Factura> obtenerFacturasCliente(String cliente) {
        return facturaRepositorio.buscaPorCliente(cliente);
    }
}