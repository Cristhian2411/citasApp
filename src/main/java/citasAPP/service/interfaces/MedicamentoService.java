package citasAPP.service.interfaces;

import citasAPP.dto.MedicamentoDto;

import java.util.List;

public interface MedicamentoService {
    MedicamentoDto crearMedicamento(MedicamentoDto dto);

    MedicamentoDto obtenerMedicamentoPorId(Long id);

    List<MedicamentoDto> listarMedicamentos();

    MedicamentoDto actualizarMedicamento(Long id, MedicamentoDto dto);

    void eliminarMedicamento(Long id);
}
