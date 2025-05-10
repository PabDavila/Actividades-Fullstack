package com.dsy2201.evento;

import com.dsy2201.evento.clases.Evento;
import com.dsy2201.evento.controlador.EventoControlador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventoControlador.class)
public class EventoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Evento crearEvento() {
        Evento evento = new Evento();
        evento.setNombre("Conferencia de Tecnología");
        evento.setFecha("2025-06-15");
        evento.setUbicacion("Santiago");
        evento.setParticipantes(List.of());
        return evento;
    }

    @Test
    public void testRegistrarEvento() throws Exception {
        Evento evento = crearEvento();

        mockMvc.perform(post("/eventos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.nombre").value("Conferencia de Tecnología"))
                .andExpect(jsonPath("$.links.length()").value(2));
    }

    @Test
    public void testObtenerDetallesEvento() throws Exception {
        Evento evento = crearEvento();

        String response = mockMvc.perform(post("/eventos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evento)))
                .andReturn().getResponse().getContentAsString();

        int id = objectMapper.readTree(response).path("content").path("id").asInt();

        mockMvc.perform(get("/eventos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.nombre").value("Conferencia de Tecnología"));
    }

    @Test
    public void testInscribirParticipante() throws Exception {
        Evento evento = crearEvento();

        String response = mockMvc.perform(post("/eventos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evento)))
                .andReturn().getResponse().getContentAsString();

        int id = objectMapper.readTree(response).path("content").path("id").asInt();

        mockMvc.perform(post("/eventos/" + id + "/inscribir?participante=Ana"))
                .andExpect(status().isOk())
                .andExpect(content().string("Participante Ana inscrito en el evento Conferencia de Tecnología"));
    }

    @Test
    public void testInscribirParticipanteEnEventoInexistente() throws Exception {
        mockMvc.perform(post("/eventos/999/inscribir?participante=Luis"))
                .andExpect(status().isOk())
                .andExpect(content().string("Evento no encontrado"));
    }
}
