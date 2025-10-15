package citasAPP.service.interfaces;

import citasAPP.dto.PacienteDto;

import java.util.List;

public interface PacienteService {

    List<PacienteDto> listarPacientes();

    PacienteDto obtenerPacientePorId(Long id);

    PacienteDto crearPaciente(PacienteDto pacienteDto);

    PacienteDto actualizarPaciente(Long id, PacienteDto pacienteDto);

    void eliminarPaciente(Long id);
}