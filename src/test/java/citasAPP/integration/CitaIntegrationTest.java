package citasAPP.integration;

import citasAPP.entity.Cita;
import citasAPP.entity.Medico;
import citasAPP.entity.Paciente;
import citasAPP.repository.CitaRepository;
import citasAPP.repository.MedicoRepository;
import citasAPP.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class CitaIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private CitaRepository citaRepository;
    @Autowired private MedicoRepository medicoRepository;
    @Autowired private PacienteRepository pacienteRepository;

    private Long citaId;

    @BeforeEach
    void setUp() {
        // Limpieza de tablas
        citaRepository.deleteAll();
        medicoRepository.deleteAll();
        pacienteRepository.deleteAll();

        // Crear médico y paciente
        Medico medico = medicoRepository.save(new Medico(
                null, "Dr. Juan Pérez", "Cardiología", "juan.perez@hospital.com", "3100000001"
        ));

        Paciente paciente = pacienteRepository.save(new Paciente(
                null, "Carlos López", "123456789", "3101111111", "carlos@example.com", "Cra 10 # 20-30"
        ));

        // Crear citas usando tu constructor actual
        Cita c1 = citaRepository.save(new Cita(
                null,
                LocalDate.of(2025, 10, 10),
                LocalTime.of(8, 0),
                "Chequeo general",
                paciente,
                medico
        ));

        citaRepository.save(new Cita(
                null,
                LocalDate.of(2025, 10, 11),
                LocalTime.of(9, 30),
                "Control mensual",
                paciente,
                medico
        ));

        citaId = c1.getId();
        System.out.println("Datos precargados: citas=" + citaRepository.count());
    }

    @Test
    void listarCitas_deberiaRetornarRegistros() throws Exception {
        mockMvc.perform(get("/api/citas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].motivo").exists());
    }

    @Test
    void obtenerCitaPorId_deberiaRetornarCitaCorrecta() throws Exception {
        mockMvc.perform(get("/api/citas/{id}", citaId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motivo").value("Chequeo general"));
    }

    @Test
    void listarCitas_deberiaCoincidirConRepositorio() {
        List<Cita> citas = citaRepository.findAll();
        assertThat(citas).hasSize(2);
        assertThat(citas.get(0).getMotivo()).isEqualTo("Chequeo general");
    }
}
