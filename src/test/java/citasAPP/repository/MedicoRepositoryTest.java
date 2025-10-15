package citasAPP.repository;

import citasAPP.entity.Medico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    void listarMedicos_deberiaRetornarCincoRegistros() {
        List<Medico> medicos = medicoRepository.findAll();
        assertEquals(5, medicos.size());
    }

    @Test
    void obtenerMedicoPorId_valido_deberiaRetornarMedico() {
        Optional<Medico> medico = medicoRepository.findById(1L);
        assertTrue(medico.isPresent());
        assertEquals("Dr. Juan Pérez", medico.get().getNombre());
    }

    @Test
    void obtenerMedicoPorId_inexistente_deberiaRetornarVacio() {
        Optional<Medico> medico = medicoRepository.findById(99L);
        assertTrue(medico.isEmpty());
    }

    @Test
    void crearMedico_deberiaInsertarNuevoMedico() {
        Medico nuevo = new Medico();
        nuevo.setNombre("Dra. Natalia Silva");
        nuevo.setEspecialidad("Ginecología");
        nuevo.setCorreo("natalia.silva@hospital.com");
        nuevo.setTelefono("3100000006");

        Medico guardado = medicoRepository.save(nuevo);

        assertNotNull(guardado.getId());
        assertEquals("Dra. Natalia Silva", guardado.getNombre());
    }

    @Test
    void actualizarMedico_deberiaModificarEspecialidad() {
        Medico medico = medicoRepository.findById(1L).orElseThrow();
        medico.setEspecialidad("Medicina Interna");

        Medico actualizado = medicoRepository.save(medico);

        assertEquals("Medicina Interna", actualizado.getEspecialidad());
    }

    @Test
    void eliminarMedico_deberiaEliminarRegistro() {
        medicoRepository.deleteById(5L);
        assertTrue(medicoRepository.findById(5L).isEmpty());
    }
}
