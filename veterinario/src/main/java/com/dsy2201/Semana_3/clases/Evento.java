package com.dsy2201.Semana_3.clases;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Evento {
    private int id;
    private String nombre;
    private String fecha;
    private String ubicacion;
    private List<String> participantes = new ArrayList<>();
}
