package com.dsy2201.veterinaria.clases;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Evento {
    private int id;
    private String descripcion;
}
