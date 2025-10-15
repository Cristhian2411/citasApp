
package citasAPP.service.impl;

        import citasAPP.dto.PacienteDto;
        import citasAPP.entity.Paciente;
        import citasAPP.repository.PacienteRepository;
        import citasAPP.service.PacienteServiceImpl;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceImplTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    private Paciente paciente;
    private PacienteDto pacienteDto;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Carlos Pérez");
        paciente.setDocumento("123456789");
        paciente.setTelefono("3101111111");
        paciente.setCorreo("carlos.perez@gmail.com");
        paciente.setDireccion("Cra 10 # 20-30");

        pacienteDto = new PacienteDto(paciente);
    }

    // ------------------ TEST LISTAR ------------------
    @Test
    void listarPacientes_deberiaRetornarListaDeDtos() {
        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(paciente));

        List<PacienteDto> lista = pacienteService.listarPacientes();

        assertEquals(1, lista.size());
        assertEquals("Carlos Pérez", lista.get(0).getNombre());
        verify(pacienteRepository, times(1)).findAll();
    }

    // ------------------ TEST OBTENER ------------------
    @Test
    void obtenerPacientePorId_valido_deberiaRetornarDto() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        PacienteDto resultado = pacienteService.obtenerPacientePorId(1L);

        assertNotNull(resultado);
        assertEquals("Carlos Pérez", resultado.getNombre());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerPacientePorId_inexistente_deberiaLanzarExcepcion() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pacienteService.obtenerPacientePorId(99L));
    }

    // ------------------ TEST CREAR ------------------
    @Test
    void crearPaciente_valido_deberiaGuardarYRetornarDto() {
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDto creado = pacienteService.crearPaciente(pacienteDto);

        assertNotNull(creado);
        assertEquals("Carlos Pérez", creado.getNombre());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    // ------------------ TEST ACTUALIZAR ------------------
    @Test
    void actualizarPaciente_valido_deberiaModificarYRetornarDto() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        pacienteDto.setNombre("Nuevo Nombre");

        PacienteDto actualizado = pacienteService.actualizarPaciente(1L, pacienteDto);

        assertEquals("Nuevo Nombre", actualizado.getNombre());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void actualizarPaciente_inexistente_deberiaLanzarExcepcion() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pacienteService.actualizarPaciente(99L, pacienteDto));
    }

    // ------------------ TEST ELIMINAR ------------------
    @Test
    void eliminarPaciente_valido_deberiaLlamarRepositorio() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);

        pacienteService.eliminarPaciente(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarPaciente_inexistente_deberiaLanzarExcepcion() {
        when(pacienteRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> pacienteService.eliminarPaciente(99L));
        verify(pacienteRepository, never()).deleteById(any());
    }
}
