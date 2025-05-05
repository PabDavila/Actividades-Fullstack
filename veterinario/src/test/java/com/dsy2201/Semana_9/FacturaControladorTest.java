package com.dsy2201.Semana_9;

import com.dsy2201.Semana_9.clases.Factura;
import com.dsy2201.Semana_9.clases.Servicio;
import com.dsy2201.Semana_9.controlador.FacturacionControlador;
import com.dsy2201.Semana_9.repositorio.FacturaRepositorio;
import com.dsy2201.Semana_9.repositorio.ServicioRepositorio;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FacturaControladorTest {

    private FacturacionControlador controlador;
    private FacturaRepositorio facturaRepoMock;
    private ServicioRepositorio servicioRepomock;

    @BeforeEach
    public void setUp() {
        facturaRepoMock = mock(FacturaRepositorio.class);
        controlador = new FacturacionControlador();

        // Setter para el repositorio
        controlador.setFacturaRepositorio(facturaRepoMock);
    }

    @Test
    public void testRegistrarFactura() {
        Servicio s1 = new Servicio(0, "Diseño", 100.0, null);
        Servicio s2 = new Servicio(0, "Soporte", 150.0, null);
        Factura factura = new Factura();
        factura.setCliente("Juan");
        factura.setServicios(Arrays.asList(s1, s2));

        when(facturaRepoMock.save(any(Factura.class))).thenReturn(factura);

        Factura resultado = controlador.registrarServicio(factura);

        assertEquals("Juan", resultado.getCliente());
        assertEquals(250.0, resultado.getTotal());
        verify(facturaRepoMock, times(1)).save(factura);
    }

    @Test
    public void testPagarFactura() {
        Factura factura = new Factura();
        factura.setId(1);
        factura.setPagada(false);

        when(facturaRepoMock.findById(1)).thenReturn(Optional.of(factura));

        String resultado = controlador.pagarFactura(1);

        assertTrue(resultado.contains("pagada"));
        assertTrue(factura.isPagada());
        verify(facturaRepoMock, times(1)).save(factura);
    }

    @AfterEach
    public void tearDown() {
        controlador = null;
    }
}
