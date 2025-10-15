package citasAPP.service;


import citasAPP.dto.MedicoDto;
import citasAPP.entity.Medico;
import citasAPP.repository.MedicoRepository;

import citasAPP.service.interfaces.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<MedicoDto> listarMedicos() {
        return medicoRepository.findAll()
                .stream()
                .map(MedicoDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDto obtenerMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
        return new MedicoDto(medico);
    }

    @Override
    public MedicoDto crearMedico(MedicoDto medicoDto) {
        Medico nuevoMedico = medicoRepository.save(medicoDto.toEntity());
        return new MedicoDto(nuevoMedico);
    }

    @Override
    public MedicoDto actualizarMedico(Long id, MedicoDto medicoDto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));

        medico.setNombre(medicoDto.getNombre());
        medico.setEspecialidad(medicoDto.getEspecialidad());
        medico.setTelefono(medicoDto.getTelefono());
        medico.setCorreo(medicoDto.getCorreo());

        Medico medicoActualizado = medicoRepository.save(medico);
        return new MedicoDto(medicoActualizado);
    }

    @Override
    public void eliminarMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico no encontrado con ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
}

