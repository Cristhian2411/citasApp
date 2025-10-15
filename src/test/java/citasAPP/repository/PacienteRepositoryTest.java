package citasAPP.repository;

import citasAPP.entity.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    void listarPacientes_deberiaRetornarCincoRegistros() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        assertEquals(5, pacientes.size());
    }

    @Test
    void obtenerPacientePorId_valido_deberiaRetornarPaciente() {
        Optional<Paciente> paciente = pacienteRepository.findById(1L);
        assertTrue(paciente.isPresent());
        assertEquals("Carlos Pérez", paciente.get().getNombre());
    }

    @Test
    void obtenerPacientePorId_inexistente_deberiaRetornarVacio() {
        Optional<Paciente> paciente = pacienteRepository.findById(99L);
        assertTrue(paciente.isEmpty());
    }

    @Test
    void crearPaciente_deberiaInsertarNuevoPaciente() {
        Paciente nuevo = new Paciente();
        nuevo.setNombre("Santiago Herrera");
        nuevo.setDocumento("554433221");
        nuevo.setTelefono("3106666666");
        nuevo.setCorreo("santiago.herrera@gmail.com");
        nuevo.setDireccion("Cl 100 # 70-50");

        Paciente guardado = pacienteRepository.save(nuevo);

        assertNotNull(guardado.getId());
        assertEquals("Santiago Herrera", guardado.getNombre());
    }

    @Test
    void actualizarPaciente_deberiaModificarTelefono() {
        Paciente paciente = pacienteRepository.findById(1L).orElseThrow();
        paciente.setTelefono("3109999999");

        Paciente actualizado = pacienteRepository.save(paciente);

        assertEquals("3109999999", actualizado.getTelefono());
    }

    @Test
    void eliminarPaciente_deberiaEliminarRegistro() {
        pacienteRepository.deleteById(5L);
        assertTrue(pacienteRepository.findById(5L).isEmpty());
    }
}
