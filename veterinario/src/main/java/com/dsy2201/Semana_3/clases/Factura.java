package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cliente;
    private double total;
    private boolean pagada;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id") // clave foránea en la tabla "servicio"
    private List<Servicio> servicios = new ArrayList<>();

    public void calcularTotal() {
        this.total = servicios.stream().mapToDouble(Servicio::getCosto).sum();
    }
}
