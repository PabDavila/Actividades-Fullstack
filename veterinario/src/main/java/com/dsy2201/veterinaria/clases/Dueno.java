package com.dsy2201.veterinaria.clases;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Dueno {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

}
