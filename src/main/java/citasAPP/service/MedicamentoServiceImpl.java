package citasAPP.service;


import citasAPP.dto.MedicamentoDto;
import citasAPP.entity.Medicamento;
import citasAPP.repository.MedicamentoRepository;

import citasAPP.service.interfaces.MedicamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public MedicamentoDto crearMedicamento(MedicamentoDto dto) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(dto.getNombre());
        medicamento.setDosis(dto.getDosis());
        medicamento.setIndicaciones(dto.getIndicaciones());

        medicamento = medicamentoRepository.save(medicamento);

        dto.setId(medicamento.getId());
        return dto;
    }

    @Override
    public MedicamentoDto obtenerMedicamentoPorId(Long id) {
        Optional<Medicamento> optional = medicamentoRepository.findById(id);
        if (optional.isEmpty()) return null;

        Medicamento medicamento = optional.get();
        MedicamentoDto dto = new MedicamentoDto();
        dto.setId(medicamento.getId());
        dto.setNombre(medicamento.getNombre());
        dto.setDosis(medicamento.getDosis());
        dto.setIndicaciones(medicamento.getIndicaciones());
        return dto;
    }

    @Override
    public List<MedicamentoDto> listarMedicamentos() {
        return medicamentoRepository.findAll().stream().map(m -> {
            MedicamentoDto dto = new MedicamentoDto();
            dto.setId(m.getId());
            dto.setNombre(m.getNombre());
            dto.setDosis(m.getDosis());
            dto.setIndicaciones(m.getIndicaciones());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public MedicamentoDto actualizarMedicamento(Long id, MedicamentoDto dto) {
        Optional<Medicamento> optional = medicamentoRepository.findById(id);
        if (optional.isEmpty()) return null;

        Medicamento medicamento = optional.get();
        medicamento.setNombre(dto.getNombre());
        medicamento.setDosis(dto.getDosis());
        medicamento.setIndicaciones(dto.getIndicaciones());

        medicamentoRepository.save(medicamento);
        dto.setId(medicamento.getId());
        return dto;
    }

    @Override
    public void eliminarMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}
