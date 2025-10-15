package citasAPP.service;

import citasAPP.dto.PacienteDto;
import citasAPP.entity.Paciente;
import citasAPP.repository.PacienteRepository;

import citasAPP.service.interfaces.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<PacienteDto> listarPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDto obtenerPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
        return new PacienteDto(paciente);
    }

    @Override
    public PacienteDto crearPaciente(PacienteDto pacienteDto) {
        Paciente nuevoPaciente = pacienteRepository.save(pacienteDto.toEntity());
        return new PacienteDto(nuevoPaciente);
    }

    @Override
    public PacienteDto actualizarPaciente(Long id, PacienteDto pacienteDto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        paciente.setNombre(pacienteDto.getNombre());
        paciente.setDocumento(pacienteDto.getDocumento());
        paciente.setTelefono(pacienteDto.getTelefono());
        paciente.setCorreo(pacienteDto.getCorreo());
        paciente.setDireccion(pacienteDto.getDireccion());

        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return new PacienteDto(pacienteActualizado);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
