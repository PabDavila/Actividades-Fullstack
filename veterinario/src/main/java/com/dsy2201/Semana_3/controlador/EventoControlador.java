package com.dsy2201.Semana_3.controlador;

import com.dsy2201.Semana_3.clases.Evento;
import com.dsy2201.Semana_3.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @PostMapping("/registrar")
    public Evento registrarEvento(@RequestBody Evento evento) {
        return eventoRepositorio.save(evento);
    }

    @PostMapping("/{id}/inscribir")
    public String inscribirParticipante(@PathVariable int id, @RequestParam String participante) {
        Optional<Evento> optionalEvento = eventoRepositorio.findById(id);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.getParticipantes().add(participante);
            eventoRepositorio.save(evento);
            return "Participante " + participante + " inscrito en el evento " + evento.getNombre();
        } else {
            return "Evento no encontrado";
        }
    }

    @GetMapping("/{id}")
    public Evento obtenerDetalles(@PathVariable int id) {
        return eventoRepositorio.findById(id).orElse(null);
    }
}
