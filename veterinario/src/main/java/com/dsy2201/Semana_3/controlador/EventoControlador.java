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

    // Crear evento
    @PostMapping("/registrar")
    public Evento registrarEvento(@RequestBody Evento evento) {
        return eventoRepositorio.save(evento);
    }

    // Obtener evento por ID
    @GetMapping("/{id}")
    public Evento obtenerEvento(@PathVariable int id) {
        return eventoRepositorio.findById(id).orElse(null);
    }

    // Agregar participante
    @PostMapping("/{id}/inscribir")
    public String inscribirParticipante(@PathVariable int id, @RequestParam String participante) {
        Optional<Evento> optional = eventoRepositorio.findById(id);
        if (optional.isPresent()) {
            Evento evento = optional.get();
            evento.getParticipantes().add(participante);
            eventoRepositorio.save(evento);
            return "Participante " + participante + " inscrito en el evento " + evento.getNombre();
        }
        return "Evento no encontrado";
    }

    // Actualizar evento
    @PutMapping("/{id}")
    public Evento actualizarEvento(@PathVariable int id, @RequestBody Evento eventoActualizado) {
        return eventoRepositorio.findById(id).map(evento -> {
            evento.setNombre(eventoActualizado.getNombre());
            evento.setFecha(eventoActualizado.getFecha());
            evento.setUbicacion(eventoActualizado.getUbicacion());
            return eventoRepositorio.save(evento);
        }).orElse(null);
    }

    // Eliminar evento
    @DeleteMapping("/{id}")
    public String eliminarEvento(@PathVariable int id) {
        if (eventoRepositorio.existsById(id)) {
            eventoRepositorio.deleteById(id);
            return "Evento eliminado";
        }
        return "Evento no encontrado";
    }
}
