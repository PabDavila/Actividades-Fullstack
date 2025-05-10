package com.dsy2201.evento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.dsy2201.evento.clases.Evento;
import com.dsy2201.evento.clases.Participante;
import com.dsy2201.evento.repositorio.EventoRepositorio;
import com.dsy2201.evento.repositorio.ParticipanteRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @PostMapping("/registrar")
    public EntityModel<Evento> registrarEvento(@RequestBody Evento evento) {
        Evento guardado = eventoRepositorio.save(evento);

        return EntityModel.of(guardado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerDetalles(guardado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerParticipantes(guardado.getId())).withRel("participantes")
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Evento> obtenerDetalles(@PathVariable int id) {
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        return EntityModel.of(evento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerDetalles(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerParticipantes(id)).withRel("participantes"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).inscribirParticipante(id, null)).withRel("inscribir_participante")
        );
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
    public CollectionModel<EntityModel<Participante>> obtenerParticipantes(@PathVariable int id) {
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        List<EntityModel<Participante>> participantes = evento.getParticipantes().stream()
                .map(participante -> EntityModel.of(participante,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerDetalles(id)).withRel("evento")))
                .collect(Collectors.toList());

        return CollectionModel.of(participantes,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoControlador.class).obtenerParticipantes(id)).withSelfRel());
    }

    // Para pruebas unitarias
    public void setEventoRepositorio(EventoRepositorio eventoRepositorio) {
        this.eventoRepositorio = eventoRepositorio;
    }

    public void setParticipanteRepositorio(ParticipanteRepositorio participanteRepositorio) {
        this.participanteRepositorio = participanteRepositorio;
    }
}
