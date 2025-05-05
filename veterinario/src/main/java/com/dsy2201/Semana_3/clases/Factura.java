package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cliente;
    private double total;
    private boolean pagada;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    public void calcularTotal() {
        if (servicios != null) {
            this.total = servicios.stream().mapToDouble(Servicio::getCosto).sum();
            servicios.forEach(s -> s.setFactura(this)); // asignar relación
        }
    }
}

