package com.dsy2201.veterinaria.clases;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Mascota {
    private int id;
    private String nombre;
    private int edad;
    private String raza;
    private Dueno dueno;
}
