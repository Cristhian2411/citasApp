package citasAPP.config;

import citasAPP.entity.Cita;
import citasAPP.entity.Medico;
import citasAPP.entity.Paciente;
import citasAPP.repository.CitaRepository;
import citasAPP.repository.MedicoRepository;
import citasAPP.repository.PacienteRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDataLoader {

    private final MedicoRepository medicoRepo;
    private final PacienteRepository pacienteRepo;
    private final CitaRepository citaRepo;

    public TestDataLoader(MedicoRepository medicoRepo, PacienteRepository pacienteRepo, CitaRepository citaRepo) {
        this.medicoRepo = medicoRepo;
        this.pacienteRepo = pacienteRepo;
        this.citaRepo = citaRepo;
    }

    /**
     *  Este método se ejecuta después de que el contexto está completamente cargado
     *    y las tablas ya existen en la base de datos H2.
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        // Médicos
        Medico m1 = new Medico(null, "Dr. Juan Pérez", "Cardiología", "3100000001", "juan.perez@hospital.com");
        Medico m2 = new Medico(null, "Dra. María López", "Pediatría", "3100000002", "maria.lopez@hospital.com");
        medicoRepo.save(m1);
        medicoRepo.save(m2);

        // Pacientes
        Paciente p1 = new Paciente(null, "Carlos Pérez", "123456789", "3101111111", "carlos.perez@gmail.com", "Calle 10 #12-34");
        Paciente p2 = new Paciente(null, "Laura Gómez", "987654321", "3102222222", "laura.gomez@gmail.com", "Carrera 15 #9-20");
        pacienteRepo.save(p1);
        pacienteRepo.save(p2);

        // Citas
        citaRepo.save(new Cita(null, m1.getId(), p1.getId(), LocalDate.of(2025, 10, 10), LocalTime.of(8, 0), "Chequeo general"));
        citaRepo.save(new Cita(null, m2.getId(), p2.getId(), LocalDate.of(2025, 10, 11), LocalTime.of(9, 30), "Control mensual"));

        System.out.println("Datos de prueba cargados exitosamente en la base de datos H2.");
    }
}
