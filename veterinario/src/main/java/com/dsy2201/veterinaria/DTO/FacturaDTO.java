package com.dsy2201.veterinaria.DTO;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FacturaDTO {
    private String cliente;
    private List<Long> serviciosIds;
}
