package com.dsy2201.Semana_3.controlador;
import com.dsy2201.Semana_3.clases.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/facturacion")
public class FacturacionControlador {
    private final Map<Integer, Factura> facturas = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/registrar")
    public synchronized Factura registrarServicio(@RequestBody Factura factura) {
        factura.setId(idCounter++);
        factura.calcularTotal();
        facturas.put(factura.getId(), factura);
        return factura;
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable int id) {
        return facturas.get(id);
    }

    @PostMapping("/{id}/pagar")
    public synchronized String pagarFactura(@PathVariable int id) {
        Factura factura = facturas.get(id);
        if (factura == null) {
            return "Factura no encontrada";
        }
        if (factura.isPagada()) {
            return "La factura ya está pagada";
        }
        factura.setPagada(true);
        return "Factura " + id + " pagada exitosamente";
    }
}
