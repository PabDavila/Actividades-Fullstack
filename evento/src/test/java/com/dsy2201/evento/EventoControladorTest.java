package com.dsy2201.evento;

import com.dsy2201.evento.clases.Evento;
import com.dsy2201.evento.controlador.EventoControlador;
import com.dsy2201.evento.repositorio.EventoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class) // Asegúrate de tener la dependencia correcta de JUnit 5
public class EventoControladorTest {

    @Mock
    private EventoRepositorio eventoRepositorio;

    @InjectMocks
    private EventoControlador eventoControlador;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(eventoControlador).build();
    }

    @Test
    public void registrarEvento() throws Exception {
        // Crear un evento de prueba
        Evento evento = new Evento(0, "Conferencia de Tecnología", "2025-06-15", "Auditorio Principal", null);

        // Configurar el mock para que guarde el evento
        when(eventoRepositorio.save(any(Evento.class))).thenReturn(evento);

        // Ejecutar el POST y verificar la respuesta
        mockMvc.perform(post("/eventos/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Conferencia de Tecnología\", \"fecha\": \"2025-06-15\", \"ubicacion\": \"Auditorio Principal\"}"))
                .andExpect(status().isOk()) // Esperamos un código 200 OK
                .andExpect(jsonPath("$.nombre").value("Conferencia de Tecnología"))
                .andExpect(jsonPath("$.fecha").value("2025-06-15"))
                .andExpect(jsonPath("$.ubicacion").value("Auditorio Principal"));
        
        // Verificar que el repositorio fue llamado
        verify(eventoRepositorio, times(1)).save(any(Evento.class));
    }
}
