package citasAPP.service;

import citasAPP.dto.OrdenMedicaDto;
import citasAPP.entity.OrdenMedica;
import citasAPP.repository.MedicoRepository;
import citasAPP.repository.PacienteRepository;
import citasAPP.repository.OrdenMedicaRepository;

import org.springframework.stereotype.Service;
import citasAPP.service.interfaces.OrdenMedicaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenMedicaServiceImpl implements OrdenMedicaService {

    private final OrdenMedicaRepository ordenMedicaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public OrdenMedicaServiceImpl(
            OrdenMedicaRepository ordenMedicaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository) {
        this.ordenMedicaRepository = ordenMedicaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public OrdenMedicaDto crearOrden(OrdenMedicaDto dto) {
        OrdenMedica orden = new OrdenMedica();
        orden.setTipoOrden(dto.getTipoOrden());
        orden.setResultado(dto.getResultado());
        orden.setDescripcion(dto.getDescripcion());
        orden.setEstado(dto.getEstado());
        orden.setFecha(dto.getFecha());

        if (dto.getMedicoId() != null)
            orden.setMedico(medicoRepository.findById(dto.getMedicoId()).orElse(null));

        if (dto.getPacienteId() != null)
            orden.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));

        orden = ordenMedicaRepository.save(orden);
        dto.setId(orden.getId());
        return dto;
    }

    @Override
    public OrdenMedicaDto obtenerOrdenPorId(Long id) {
        Optional<OrdenMedica> optional = ordenMedicaRepository.findById(id);
        if (optional.isEmpty()) return null;

        OrdenMedica orden = optional.get();
        OrdenMedicaDto dto = new OrdenMedicaDto();
        dto.setId(orden.getId());
        dto.setTipoOrden(orden.getTipoOrden());
        dto.setResultado(orden.getResultado());
        dto.setDescripcion(orden.getDescripcion());
        dto.setEstado(orden.getEstado());
        dto.setFecha(orden.getFecha());
        dto.setMedicoId(orden.getMedico() != null ? orden.getMedico().getId() : null);
        dto.setPacienteId(orden.getPaciente() != null ? orden.getPaciente().getId() : null);
        return dto;
    }

    @Override
    public List<OrdenMedicaDto> listarOrdenes() {
        return ordenMedicaRepository.findAll().stream().map(o -> {
            OrdenMedicaDto dto = new OrdenMedicaDto();
            dto.setId(o.getId());
            dto.setTipoOrden(o.getTipoOrden());
            dto.setResultado(o.getResultado());
            dto.setDescripcion(o.getDescripcion());
            dto.setEstado(o.getEstado());
            dto.setFecha(o.getFecha());
            dto.setMedicoId(o.getMedico() != null ? o.getMedico().getId() : null);
            dto.setPacienteId(o.getPaciente() != null ? o.getPaciente().getId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrdenMedicaDto actualizarOrden(Long id, OrdenMedicaDto dto) {
        Optional<OrdenMedica> optional = ordenMedicaRepository.findById(id);
        if (optional.isEmpty()) return null;

        OrdenMedica orden = optional.get();
        orden.setTipoOrden(dto.getTipoOrden());
        orden.setResultado(dto.getResultado());
        orden.setDescripcion(dto.getDescripcion());
        orden.setEstado(dto.getEstado());
        orden.setFecha(dto.getFecha());

        if (dto.getMedicoId() != null)
            orden.setMedico(medicoRepository.findById(dto.getMedicoId()).orElse(null));

        if (dto.getPacienteId() != null)
            orden.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));

        ordenMedicaRepository.save(orden);
        dto.setId(orden.getId());
        return dto;
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenMedicaRepository.deleteById(id);
    }
}
