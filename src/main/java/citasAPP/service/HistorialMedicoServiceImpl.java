package citasAPP.service;


import citasAPP.dto.HistorialMedicoDto;
import citasAPP.entity.HistorialMedico;
import citasAPP.repository.HistorialMedicoRepository;
import citasAPP.repository.MedicoRepository;
import citasAPP.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import citasAPP.service.interfaces.HistorialMedicoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialMedicoServiceImpl implements HistorialMedicoService {

    private final HistorialMedicoRepository historialRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public HistorialMedicoServiceImpl(HistorialMedicoRepository historialRepository,
                                      PacienteRepository pacienteRepository,
                                      MedicoRepository medicoRepository) {
        this.historialRepository = historialRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public HistorialMedicoDto crearHistorial(HistorialMedicoDto dto) {
        HistorialMedico historial = new HistorialMedico();
        historial.setFechaRegistro(dto.getFechaRegistro());
        historial.setDiagnostico(dto.getDiagnostico());
        historial.setTratamiento(dto.getTratamiento());

        if (dto.getPacienteId() != null)
            historial.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));

        if (dto.getMedicoId() != null)
            historial.setMedico(medicoRepository.findById(dto.getMedicoId()).orElse(null));

        historial = historialRepository.save(historial);
        dto.setId(historial.getId());
        return dto;
    }

    @Override
    public HistorialMedicoDto obtenerHistorialPorId(Long id) {
        Optional<HistorialMedico> optional = historialRepository.findById(id);
        if (optional.isEmpty()) return null;

        HistorialMedico historial = optional.get();
        HistorialMedicoDto dto = new HistorialMedicoDto();
        dto.setId(historial.getId());
        dto.setFechaRegistro(historial.getFechaRegistro());
        dto.setDiagnostico(historial.getDiagnostico());
        dto.setTratamiento(historial.getTratamiento());
        dto.setPacienteId(historial.getPaciente() != null ? historial.getPaciente().getId() : null);
        dto.setMedicoId(historial.getMedico() != null ? historial.getMedico().getId() : null);
        return dto;
    }

    @Override
    public List<HistorialMedicoDto> listarHistoriales() {
        return historialRepository.findAll().stream().map(h -> {
            HistorialMedicoDto dto = new HistorialMedicoDto();
            dto.setId(h.getId());
            dto.setFechaRegistro(h.getFechaRegistro());
            dto.setDiagnostico(h.getDiagnostico());
            dto.setTratamiento(h.getTratamiento());
            dto.setPacienteId(h.getPaciente() != null ? h.getPaciente().getId() : null);
            dto.setMedicoId(h.getMedico() != null ? h.getMedico().getId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public HistorialMedicoDto actualizarHistorial(Long id, HistorialMedicoDto dto) {
        Optional<HistorialMedico> optional = historialRepository.findById(id);
        if (optional.isEmpty()) return null;

        HistorialMedico historial = optional.get();
        historial.setFechaRegistro(dto.getFechaRegistro());
        historial.setDiagnostico(dto.getDiagnostico());
        historial.setTratamiento(dto.getTratamiento());

        if (dto.getPacienteId() != null)
            historial.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));

        if (dto.getMedicoId() != null)
            historial.setMedico(medicoRepository.findById(dto.getMedicoId()).orElse(null));

        historialRepository.save(historial);
        dto.setId(historial.getId());
        return dto;
    }

    @Override
    public void eliminarHistorial(Long id) {
        historialRepository.deleteById(id);
    }
}

