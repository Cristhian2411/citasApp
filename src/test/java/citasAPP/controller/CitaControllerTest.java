package citasAPP.controller;

import citasAPP.config.TestSecurityConfig;
import citasAPP.entity.Cita;
import citasAPP.service.interfaces.CitaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestSecurityConfig.class)
@WebMvcTest(CitaController.class)
class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitaService citaService;

    @Autowired
    private ObjectMapper objectMapper;

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

    // ✅ Listar citas
    @Test
    void listarCitas_deberiaRetornarListaDeCitas() throws Exception {
        List<Cita> citas = Arrays.asList(citaValida, new Cita());
        when(citaService.listarCitas()).thenReturn(citas);

        mockMvc.perform(get("/api/citas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(citaService, times(1)).listarCitas();
    }

    // ✅ Obtener cita válida
    @Test
    void obtenerCitaPorId_valido_deberiaRetornarCita() throws Exception {
        when(citaService.obtenerCitaPorId(1L)).thenReturn(citaValida);

        mockMvc.perform(get("/api/citas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(citaService, times(1)).obtenerCitaPorId(1L);
    }

    // ✅ Obtener cita inválida
    @Test
    void obtenerCitaPorId_invalido_deberiaRetornarBadRequest() throws Exception {
        when(citaService.obtenerCitaPorId(99L))
                .thenThrow(new IllegalArgumentException("El ID no existe"));

        mockMvc.perform(get("/api/citas/99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Error al obtener la cita")));

        verify(citaService, times(1)).obtenerCitaPorId(99L);
    }

    // ✅ Crear cita válida
    @Test
    void crearCita_valida_deberiaRetornarOk() throws Exception {
        doNothing().when(citaService).crearCita(any(Cita.class));

        mockMvc.perform(post("/api/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaValida)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cita creada correctamente."));

        verify(citaService, times(1)).crearCita(any(Cita.class));
    }

    // ✅ Crear cita inválida
    @Test
    void crearCita_invalida_deberiaRetornarBadRequest() throws Exception {
        doThrow(new IllegalArgumentException("La fecha y hora son obligatorias"))
                .when(citaService).crearCita(any(Cita.class));

        mockMvc.perform(post("/api/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaValida)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Error al crear la cita")));

        verify(citaService, times(1)).crearCita(any(Cita.class));
    }

    // ✅ Actualizar cita válida
    @Test
    void actualizarCita_valida_deberiaRetornarOk() throws Exception {
        doNothing().when(citaService).actualizarCita(any(Cita.class));

        mockMvc.perform(put("/api/citas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaValida)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cita actualizada correctamente."));

        verify(citaService, times(1)).actualizarCita(any(Cita.class));
    }

    // ✅ Actualizar cita inválida
    @Test
    void actualizarCita_invalida_deberiaRetornarBadRequest() throws Exception {
        doThrow(new IllegalArgumentException("ID inválido"))
                .when(citaService).actualizarCita(any(Cita.class));

        mockMvc.perform(put("/api/citas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaValida)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Error al actualizar la cita")));

        verify(citaService, times(1)).actualizarCita(any(Cita.class));
    }

    // ✅ Eliminar cita válida
    @Test
    void eliminarCita_valida_deberiaRetornarOk() throws Exception {
        doNothing().when(citaService).eliminarCita(1L);

        mockMvc.perform(delete("/api/citas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cita eliminada correctamente."));

        verify(citaService, times(1)).eliminarCita(1L);
    }

    // ✅ Eliminar cita inválida
    @Test
    void eliminarCita_invalida_deberiaRetornarBadRequest() throws Exception {
        doThrow(new IllegalArgumentException("Debe proporcionar un ID válido"))
                .when(citaService).eliminarCita(anyLong());

        mockMvc.perform(delete("/api/citas/99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Error al eliminar la cita")));

        verify(citaService, times(1)).eliminarCita(99L);
    }
}
