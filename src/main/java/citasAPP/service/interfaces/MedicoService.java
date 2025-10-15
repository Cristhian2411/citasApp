package citasAPP.service.interfaces;

import citasAPP.dto.MedicoDto;

import java.util.List;

public interface MedicoService {

    List<MedicoDto> listarMedicos();

    MedicoDto obtenerMedicoPorId(Long id);

    MedicoDto crearMedico(MedicoDto medicoDto);

    MedicoDto actualizarMedico(Long id, MedicoDto medicoDto);

    void eliminarMedico(Long id);
}
