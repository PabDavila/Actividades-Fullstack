package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Servicio {
    private String nombre;
    private double costo;

}
