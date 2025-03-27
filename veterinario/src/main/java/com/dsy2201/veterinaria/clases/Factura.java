package com.dsy2201.veterinaria.clases;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cliente;
    
    private LocalDate fecha = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Servicio> servicios;
    
    private double total;
    
    private boolean pagada = false;
}
