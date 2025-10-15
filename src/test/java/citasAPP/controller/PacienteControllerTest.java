package citasAPP.controller;

import citasAPP.dto.PacienteDto;
import citasAPP.service.interfaces.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PacienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private PacienteDto paciente1;
    private PacienteDto paciente2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteController).build();

        paciente1 = new PacienteDto();
        paciente1.setId(1L);
        paciente1.setNombre("Juan Pérez");
        paciente1.setDocumento("123456789");
        paciente1.setTelefono("3001234567");
        paciente1.setCorreo("juanp@example.com");
        paciente1.setDireccion("Calle 123");

        paciente2 = new PacienteDto();
        paciente2.setId(2L);
        paciente2.setNombre("Ana López");
        paciente2.setDocumento("987654321");
        paciente2.setTelefono("3119876543");
        paciente2.setCorreo("ana@example.com");
        paciente2.setDireccion("Carrera 45");
    }

    @Test
    void listarPacientes_deberiaRetornarLista() throws Exception {
        List<PacienteDto> pacientes = Arrays.asList(paciente1, paciente2);
        when(pacienteService.listarPacientes()).thenReturn(pacientes);

        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Juan Pérez")))
                .andExpect(jsonPath("$[1].nombre", is("Ana López")));

        verify(pacienteService, times(1)).listarPacientes();
    }

    @Test
    void obtenerPacientePorId_deberiaRetornarPaciente() throws Exception {
        when(pacienteService.obtenerPacientePorId(1L)).thenReturn(paciente1);

        mockMvc.perform(get("/api/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan Pérez")));

        verify(pacienteService, times(1)).obtenerPacientePorId(1L);
    }

    @Test
    void crearPaciente_deberiaRetornarNuevoPaciente() throws Exception {
        when(pacienteService.crearPaciente(any(PacienteDto.class))).thenReturn(paciente1);

        mockMvc.perform(post("/api/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan Pérez")));

        verify(pacienteService, times(1)).crearPaciente(any(PacienteDto.class));
    }

    @Test
    void actualizarPaciente_deberiaRetornarActualizado() throws Exception {
        when(pacienteService.actualizarPaciente(eq(1L), any(PacienteDto.class))).thenReturn(paciente2);

        mockMvc.perform(put("/api/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Ana López")));

        verify(pacienteService, times(1)).actualizarPaciente(eq(1L), any(PacienteDto.class));
    }

    @Test
    void eliminarPaciente_deberiaRetornarStatusOk() throws Exception {
        doNothing().when(pacienteService).eliminarPaciente(1L);

        mockMvc.perform(delete("/api/pacientes/1"))
                .andExpect(status().isOk());

        verify(pacienteService, times(1)).eliminarPaciente(1L);
    }
}
