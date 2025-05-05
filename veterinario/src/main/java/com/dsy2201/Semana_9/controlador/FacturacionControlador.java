package com.dsy2201.Semana_9.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dsy2201.Semana_9.clases.Factura;
import com.dsy2201.Semana_9.repositorio.FacturaRepositorio;

import java.util.Optional;

@RestController
@RequestMapping("/facturacion")
public class FacturacionControlador {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    @PostMapping("/registrar")
    public Factura registrarFactura(@RequestBody Factura factura) {
        factura.calcularTotal(); // Suma automática de costos
        return facturaRepositorio.save(factura);
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable int id) {
        return facturaRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/{id}/pagar")
    public String pagarFactura(@PathVariable int id) {
        Optional<Factura> optional = facturaRepositorio.findById(id);
        if (optional.isPresent()) {
            Factura factura = optional.get();
            if (factura.isPagada()) {
                return "La factura ya está pagada";
            }
            factura.setPagada(true);
            facturaRepositorio.save(factura);
            return "Factura " + id + " pagada exitosamente";
        }
        return "Factura no encontrada";
    }

    public Factura registrarServicio(Factura factura) {
        return factura;
    }

    public void setFacturaRepositorio(FacturaRepositorio facturaRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
    }
}
