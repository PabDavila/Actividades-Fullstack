package com.dsy2201.veterinaria.clases;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Veterinario {
    private int id;
    private String nombre;
    private String apellido;
}