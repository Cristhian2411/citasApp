package citasAPP.service.interfaces;

import citasAPP.entity.Cita;

import java.util.List;

public interface CitaService {

    Cita obtenerCitaPorId(Long id);

    void crearCita(Cita cita);

    void actualizarCita(Cita cita);

    void eliminarCita(Long id);

    List<Cita> listarCitas();
}