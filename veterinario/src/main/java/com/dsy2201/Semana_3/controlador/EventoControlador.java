package com.dsy2201.Semana_3.controlador;

import org.springframework.web.bind.annotation.*;
import com.dsy2201.Semana_3.clases.Evento;

import java.util.*;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {
    private final Map<Integer, Evento> eventos = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/registrar")
    public synchronized Evento registrarEvento(@RequestBody Evento evento) {
        evento.setId(idCounter++);
        eventos.put(evento.getId(), evento);
        return evento;
    }

    @PostMapping("/{id}/inscribir")
    public synchronized String inscribirParticipante(@PathVariable int id, @RequestParam String participante) {
        Evento evento = eventos.get(id);
        if (evento == null) {
            return "Evento no encontrado";
        }
        evento.getParticipantes().add(participante);
        return "Participante " + participante + " inscrito en el evento " + evento.getNombre();
    }

    @GetMapping("/{id}")
    public Evento obtenerDetalles(@PathVariable int id) {
        return eventos.get(id);
    }
}
