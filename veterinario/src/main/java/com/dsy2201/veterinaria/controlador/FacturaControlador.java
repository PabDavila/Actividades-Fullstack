package com.dsy2201.veterinaria.controlador;

import com.dsy2201.veterinaria.DTO.FacturaDTO;
import com.dsy2201.veterinaria.DTO.PagoDTO;
import com.dsy2201.veterinaria.clases.Factura;
import com.dsy2201.veterinaria.servicio.FacturaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaControlador {
    
    private final FacturaServicio facturaServicio;

    public FacturaControlador(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    @PostMapping
    public Factura generarFactura(@RequestBody FacturaDTO facturaDTO) {
        return facturaServicio.generarFactura(facturaDTO);
    }

    @PutMapping("/pagar")
    public Factura pagarFactura(@RequestBody PagoDTO pagoDTO) {
        return facturaServicio.pagarFactura(pagoDTO.getFacturaId());
    }

    @GetMapping("/{cliente}")
    public List<Factura> obtenerFacturas(@PathVariable String cliente) {
        return facturaServicio.obtenerFacturasCliente(cliente);
    }
}
