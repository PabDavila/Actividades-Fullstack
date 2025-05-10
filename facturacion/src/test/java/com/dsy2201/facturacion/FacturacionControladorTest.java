package com.dsy2201.facturacion;

import com.dsy2201.facturacion.aplicacion.FacturacionApplication;
import com.dsy2201.facturacion.clases.Factura;
import com.dsy2201.facturacion.clases.Servicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = FacturacionApplication.class)
public class FacturacionControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Factura crearFactura() {
        Factura factura = new Factura();
        factura.setCliente("Juan Pérez");

        List<Servicio> servicios = new ArrayList<>();
        Servicio s1 = new Servicio();
        s1.setDescripcion("Asesoría técnica");
        s1.setCosto(120.0);
        Servicio s2 = new Servicio();
        s2.setDescripcion("Instalación de software");
        s2.setCosto(80.0);

        servicios.add(s1);
        servicios.add(s2);

        factura.setServicios(servicios);
        return factura;
    }

    @Test
    public void testRegistrarFactura() throws Exception {
        Factura factura = crearFactura();

        mockMvc.perform(post("/facturacion/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(factura)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.cliente").value("Juan Pérez"))
                .andExpect(jsonPath("$.content.total").value(200.0));
    }

    @Test
    public void testObtenerFactura() throws Exception {
        Factura factura = crearFactura();

        String response = mockMvc.perform(post("/facturacion/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(factura)))
                .andReturn().getResponse().getContentAsString();

        int id = objectMapper.readTree(response).path("content").path("id").asInt();

        mockMvc.perform(get("/facturacion/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.cliente").value("Juan Pérez"));
    }

    @Test
    public void testPagarFactura() throws Exception {
        Factura factura = crearFactura();

        String response = mockMvc.perform(post("/facturacion/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(factura)))
                .andReturn().getResponse().getContentAsString();

        int id = objectMapper.readTree(response).path("content").path("id").asInt();

        mockMvc.perform(post("/facturacion/" + id + "/pagar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura " + id + " pagada exitosamente"));
    }

    @Test
    public void testPagarFacturaInexistente() throws Exception {
        mockMvc.perform(post("/facturacion/999/pagar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factura no encontrada"));
    }
}
