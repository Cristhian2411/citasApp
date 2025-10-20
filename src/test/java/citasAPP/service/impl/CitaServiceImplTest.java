package citasAPP.service.impl;

import citasAPP.entity.Cita;
import citasAPP.repository.CitaRepository;
import citasAPP.service.CitaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceImplTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaServiceImpl citaService;

    private Cita citaValida;

    @BeforeEach
    void setUp() {
        citaValida = new Cita();
        citaValida.setId(1L);
        citaValida.setFecha(LocalDate.of(2025, 10, 13));
        citaValida.setHora(LocalTime.of(10, 30));
        citaValida.setIdMedico(2L);
        citaValida.setIdPaciente(3L);
    }

    //Test crear cita

    @Test
    void crearCita_valida_deberiaGuardarCita() {
        citaService.crearCita(citaValida);
        verify(citaRepository, times(1)).save(citaValida);
    }

    @Test
    void crearCita_sinFecha_deberiaLanzarExcepcion() {
        citaValida.setFecha(null);
        assertThrows(IllegalArgumentException.class, () -> citaService.crearCita(citaValida));
        verify(citaRepository, never()).save(any());
    }

    @Test
    void crearCita_sinMedicoOPaciente_deberiaLanzarExcepcion() {
        citaValida.setIdMedico(null);
        assertThrows(IllegalArgumentException.class, () -> citaService.crearCita(citaValida));
        verify(citaRepository, never()).save(any());
    }

    @Test
    void crearCita_fueraDeHorario_deberiaLanzarExcepcion() {
        citaValida.setHora(LocalTime.of(21, 0)); // después de las 20:00
        assertThrows(IllegalArgumentException.class, () -> citaService.crearCita(citaValida));
        verify(citaRepository, never()).save(any());
    }

    //Test obtener cita

    @Test
    void obtenerCitaPorId_valido_deberiaRetornarCita() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(citaValida));

        Cita resultado = citaService.obtenerCitaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(citaRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerCitaPorId_idNulo_deberiaLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> citaService.obtenerCitaPorId(null));
        verify(citaRepository, never()).findById(any());
    }

    @Test
    void obtenerCitaPorId_inexistente_deberiaLanzarExcepcion() {
        when(citaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> citaService.obtenerCitaPorId(99L));
    }

    //Test actualizar cita

    @Test
    void actualizarCita_valida_deberiaGuardarCita() {
        when(citaRepository.existsById(1L)).thenReturn(true);
        citaService.actualizarCita(citaValida);
        verify(citaRepository, times(1)).save(citaValida);
    }

    @Test
    void actualizarCita_sinId_deberiaLanzarExcepcion() {
        citaValida.setId(null);
        assertThrows(IllegalArgumentException.class, () -> citaService.actualizarCita(citaValida));
        verify(citaRepository, never()).save(any());
    }

    @Test
    void actualizarCita_inexistente_deberiaLanzarExcepcion() {
        when(citaRepository.existsById(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> citaService.actualizarCita(citaValida));
        verify(citaRepository, never()).save(any());
    }

    // Test eliminar cita

    @Test
    void eliminarCita_valido_deberiaEliminarCita() {
        when(citaRepository.existsById(1L)).thenReturn(true);
        citaService.eliminarCita(1L);
        verify(citaRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarCita_idNulo_deberiaLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> citaService.eliminarCita(null));
        verify(citaRepository, never()).deleteById(any());
    }

    @Test
    void eliminarCita_inexistente_deberiaLanzarExcepcion() {
        when(citaRepository.existsById(99L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> citaService.eliminarCita(99L));
        verify(citaRepository, never()).deleteById(any());
    }

    //test listar citas

    @Test
    void listarCitas_deberiaRetornarListaDeCitas() {
        when(citaRepository.findAll()).thenReturn(Arrays.asList(citaValida, new Cita()));

        List<Cita> citas = citaService.listarCitas();

        assertNotNull(citas);
        assertEquals(2, citas.size());
        verify(citaRepository, times(1)).findAll();
    }
}
