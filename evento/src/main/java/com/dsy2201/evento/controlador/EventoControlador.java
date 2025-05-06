package com.dsy2201.evento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dsy2201.evento.clases.Evento;
import com.dsy2201.evento.clases.Participante;
import com.dsy2201.evento.repositorio.EventoRepositorio;
import com.dsy2201.evento.repositorio.ParticipanteRepositorio;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @PostMapping("/registrar")
    public Evento registrarEvento(@RequestBody Evento evento) {
        return eventoRepositorio.save(evento);
    }

    @GetMapping("/{id}")
    public Evento obtenerDetalles(@PathVariable int id) {
        return eventoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
    }

    @PostMapping("/{id}/inscribir")
    public String inscribirParticipante(@PathVariable int id, @RequestParam String nombre) {
        Optional<Evento> eventoOpt = eventoRepositorio.findById(id);
        if (eventoOpt.isEmpty()) {
            return "Evento no encontrado con ID: " + id;
        }

        Participante participante = new Participante();
        participante.setNombre(nombre);
        participante.setEvento(eventoOpt.get());

        participanteRepositorio.save(participante);
        return "Participante " + nombre + " inscrito correctamente en el evento " + eventoOpt.get().getNombre();
    }

    @GetMapping("/{id}/participantes")
    public List<Participante> obtenerParticipantes(@PathVariable int id) {
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        return evento.getParticipantes();
    }
    
    public void setEventoRepositorio(EventoRepositorio eventoRepositorio) {
        this.eventoRepositorio = eventoRepositorio;
    }
    
    public void setParticipanteRepositorio(ParticipanteRepositorio participanteRepositorio) {
        this.participanteRepositorio = participanteRepositorio;
    }
    
}
