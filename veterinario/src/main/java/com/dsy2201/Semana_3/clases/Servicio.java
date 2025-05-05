package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;
    private double costo;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;
}
