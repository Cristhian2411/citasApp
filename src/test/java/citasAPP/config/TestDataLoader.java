package citasAPP.config;

import citasAPP.entity.Cita;
import citasAPP.entity.Medico;
import citasAPP.entity.Paciente;
import citasAPP.repository.CitaRepository;
import citasAPP.repository.MedicoRepository;
import citasAPP.repository.PacienteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
@Profile("test") // 🔹 se ejecuta solo cuando el perfil es "test"
public class TestDataLoader {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;

    /**
     * Cargamos datos iniciales solo para los tests.
     * Usamos una nueva transacción para evitar errores de "prepared statement already exists".
     */
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initData() {

        Paciente paciente1 = new Paciente(null, "Juan Pérez", "123456789", "3001234567", "juan@example.com", "Calle 123");
        Paciente paciente2 = new Paciente(null, "Ana López", "987654321", "3119876543", "ana@example.com", "Carrera 45");
        pacienteRepository.save(paciente1);
        pacienteRepository.save(paciente2);

        Medico medico = new Medico(null, "Carlos Ruiz", "Cardiología", "3125556677", "carlos@example.com");
        medicoRepository.save(medico);

        citaRepository.save(new Cita(null, LocalDate.now(), LocalTime.of(10, 0), "Chequeo general", paciente1, medico));
        citaRepository.save(new Cita(null, LocalDate.now().plusDays(1), LocalTime.of(15, 0), "Control de presión", paciente2, medico));
    }
}
