package citasAPP.service;

import citasAPP.dto.FormulaMedicaDto;
import citasAPP.entity.FormulaMedica;
import citasAPP.repository.FormulaMedicaRepository;

import citasAPP.service.interfaces.FormulaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormulaMedicaServiceImpl implements FormulaMedicaService {

    @Autowired
    private FormulaMedicaRepository formulaMedicaRepository;

    @Override
    public FormulaMedicaDto obtenerFormulaPorId(Long id) {
        FormulaMedica formula = formulaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fórmula médica no encontrada con ID: " + id));
        return new FormulaMedicaDto(formula);
    }

    @Override
    public FormulaMedicaDto crearFormula(FormulaMedicaDto dto) {
        FormulaMedica formula = dto.toEntity();
        FormulaMedica nueva = formulaMedicaRepository.save(formula);
        return new FormulaMedicaDto(nueva);
    }

    @Override
    public FormulaMedicaDto actualizarFormula(Long id, FormulaMedicaDto dto) {
        FormulaMedica formula = formulaMedicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fórmula médica no encontrada con ID: " + id));

        formula.setFecha(dto.getFecha());
        formula.setObservaciones(dto.getObservaciones());

        FormulaMedica actualizada = formulaMedicaRepository.save(formula);
        return new FormulaMedicaDto(actualizada);
    }

    @Override
    public void eliminarFormula(Long id) {
        formulaMedicaRepository.deleteById(id);
    }

    @Override
    public List<FormulaMedicaDto> listarFormulas() {
        return formulaMedicaRepository.findAll()
                .stream()
                .map(FormulaMedicaDto::new)
                .collect(Collectors.toList());
    }
}
