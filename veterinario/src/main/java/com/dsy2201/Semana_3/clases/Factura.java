package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter

public class Factura {
    private int id;
    private String cliente;
    private List<Servicio> servicios = new ArrayList<>();
    private double total;
    private boolean pagada;

    public void calcularTotal() {
        this.total = servicios.stream().mapToDouble(Servicio::getCosto).sum();
}
}