package citasAPP.service.interfaces;

import citasAPP.dto.FormulaMedicaDto;

import java.util.List;

public interface FormulaMedicaService {
    FormulaMedicaDto obtenerFormulaPorId(Long id);

    FormulaMedicaDto crearFormula(FormulaMedicaDto dto);

    FormulaMedicaDto actualizarFormula(Long id, FormulaMedicaDto dto);

    void eliminarFormula(Long id);

    List<FormulaMedicaDto> listarFormulas();
}

