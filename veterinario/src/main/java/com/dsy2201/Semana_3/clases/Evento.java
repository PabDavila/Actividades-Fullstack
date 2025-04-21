package com.dsy2201.Semana_3.clases;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String fecha;
    private String ubicacion;

    @ElementCollection
    @CollectionTable(name = "evento_participantes", joinColumns = @JoinColumn(name = "evento_id"))
    @Column(name = "participante")
    private List<String> participantes = new ArrayList<>();
}
