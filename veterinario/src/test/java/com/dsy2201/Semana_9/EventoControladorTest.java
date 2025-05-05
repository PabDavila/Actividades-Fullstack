package com.dsy2201.Semana_9;

import com.dsy2201.Semana_9.clases.Evento;
import com.dsy2201.Semana_9.clases.Participante;
import com.dsy2201.Semana_9.controlador.EventoControlador;
import com.dsy2201.Semana_9.repositorio.EventoRepositorio;
import com.dsy2201.Semana_9.repositorio.ParticipanteRepositorio;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventoControladorTest {

    private EventoControlador controlador;
    private EventoRepositorio eventoRepoMock;
    private ParticipanteRepositorio participanteRepoMock;

    @BeforeEach
    public void setUp() {
        eventoRepoMock = mock(EventoRepositorio.class);
        participanteRepoMock = mock(ParticipanteRepositorio.class);
        controlador = new EventoControlador();

        // Usar setters públicos
        controlador.setEventoRepositorio(eventoRepoMock);
        controlador.setParticipanteRepositorio(participanteRepoMock);
    }

    @Test
    public void testRegistrarEvento() {
        Evento evento = new Evento();
        evento.setNombre("Conferencia");
        evento.setFecha("2025-05-10");
        evento.setUbicacion("Auditorio A");

        when(eventoRepoMock.save(any(Evento.class))).thenReturn(evento);

        Evento resultado = controlador.registrarEvento(evento);

        assertEquals("Conferencia", resultado.getNombre());
        verify(eventoRepoMock, times(1)).save(evento);
    }

    @Test
    public void testInscribirParticipante() {
        Evento evento = new Evento();
        evento.setId(1);
        evento.setNombre("Taller");
        evento.setParticipantes(new ArrayList<>());

        when(eventoRepoMock.findById(1)).thenReturn(Optional.of(evento));

        String resultado = controlador.inscribirParticipante(1, "Juan Pérez");

        assertTrue(resultado.contains("inscrito"));
        verify(participanteRepoMock, times(1)).save(any(Participante.class));
    }

    @AfterEach
    public void tearDown() {
        controlador = null;
    }
}