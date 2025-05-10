package com.dsy2201.facturacion.controlador;

import com.dsy2201.facturacion.clases.Factura;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/facturacion")
public class FacturacionControlador {
    private final Map<Integer, Factura> facturas = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/registrar")
    public synchronized EntityModel<Factura> registrarServicio(@RequestBody Factura factura) {
        factura.setId(idCounter++);
        factura.calcularTotal();
        facturas.put(factura.getId(), factura);

        return EntityModel.of(factura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FacturacionControlador.class).obtenerFactura(factura.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FacturacionControlador.class).pagarFactura(factura.getId())).withRel("pagar_factura")
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Factura> obtenerFactura(@PathVariable int id) {
        Factura factura = facturas.get(id);
        if (factura == null) {
            throw new NoSuchElementException("Factura no encontrada");
        }

        return EntityModel.of(factura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FacturacionControlador.class).obtenerFactura(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FacturacionControlador.class).pagarFactura(id)).withRel("pagar_factura")
        );
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
