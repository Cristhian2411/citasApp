package citasAPP.service;

import citasAPP.entity.Cita;
import citasAPP.repository.CitaRepository;
import citasAPP.service.interfaces.CitaService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Cita obtenerCitaPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la cita no puede ser nulo");
        }

        // El método findById devuelve un Optional
        return citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la cita con ID: " + id));
    }

    @Override
    public void crearCita(Cita cita) {
        if (cita.getFecha() == null || cita.getHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        if (cita.getIdMedico() == null || cita.getIdPaciente() == null) {
            throw new IllegalArgumentException("El médico y el paciente son obligatorios");
        }

        // Validación de horario
        if (cita.getHora().isBefore(LocalTime.of(7, 0)) || cita.getHora().isAfter(LocalTime.of(20, 0))) {
            throw new IllegalArgumentException("Las citas solo pueden agendarse entre las 7:00 y las 20:00 horas");
        }

        citaRepository.save(cita);
    }

    @Override
    public void actualizarCita(Cita cita) {
        if (cita.getId() == null) {
            throw new IllegalArgumentException("El ID de la cita es obligatorio para actualizarla");
        }

        if (!citaRepository.existsById(cita.getId())) {
            throw new IllegalArgumentException("No existe una cita con ID: " + cita.getId());
        }

        citaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Debe proporcionar un ID válido para eliminar la cita");
        }

        if (!citaRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe una cita con ID: " + id);
        }

        citaRepository.deleteById(id);
    }

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
}
