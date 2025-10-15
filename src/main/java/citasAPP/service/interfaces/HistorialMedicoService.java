package citasAPP.service.interfaces;


import citasAPP.dto.HistorialMedicoDto;

import java.util.List;

public interface HistorialMedicoService {
    HistorialMedicoDto crearHistorial(HistorialMedicoDto dto);

    HistorialMedicoDto obtenerHistorialPorId(Long id);

    List<HistorialMedicoDto> listarHistoriales();

    HistorialMedicoDto actualizarHistorial(Long id, HistorialMedicoDto dto);

    void eliminarHistorial(Long id);
}

