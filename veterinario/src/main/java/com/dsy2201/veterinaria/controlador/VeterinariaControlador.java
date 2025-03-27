package com.dsy2201.veterinaria.controlador;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import com.dsy2201.veterinaria.clases.Dueno;
import com.dsy2201.veterinaria.clases.Evento;
import com.dsy2201.veterinaria.clases.Mascota;
import com.dsy2201.veterinaria.clases.Servicio;
import com.dsy2201.veterinaria.clases.Veterinario;


@RestController
public class VeterinariaControlador {
    private List<Veterinario> veterinarios = new ArrayList<>();
    private List<Dueno> duenos = new ArrayList<>();
    private List<Mascota> mascotas = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    public VeterinariaControlador(){
        veterinarios.add(new Veterinario(0, "Roberto", "Perez"));
        duenos.add(new Dueno(0, "12.456.337-9", "Vicente", "Paredes"));
        mascotas.add(new Mascota(0, "Sultan", 5, "Pastor Aleman", null));
        servicios.add(new Servicio(0, "Corte de Pelo", 10000));
        eventos.add(new Evento(0, "Concurso"));
    }

    @GetMapping("/veterinarios")
    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }
    
    @GetMapping("/duenos")
    public List<Dueno> getDuenos() {
        return duenos;
    }

    @GetMapping("/mascotas")
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    @GetMapping("/servicios")
    public List<Servicio> getServicios() {
        return servicios;
    }

    @GetMapping("/eventos")
    public List<Evento> getEventos() {
        return eventos;
    }


}
