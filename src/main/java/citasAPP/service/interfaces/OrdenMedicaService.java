package citasAPP.service.interfaces;

import citasAPP.dto.OrdenMedicaDto;

import java.util.List;

public interface OrdenMedicaService {
    OrdenMedicaDto crearOrden(OrdenMedicaDto dto);

    OrdenMedicaDto obtenerOrdenPorId(Long id);

    List<OrdenMedicaDto> listarOrdenes();

    OrdenMedicaDto actualizarOrden(Long id, OrdenMedicaDto dto);

    void eliminarOrden(Long id);
}
