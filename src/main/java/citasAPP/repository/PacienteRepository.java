package citasAPP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import citasAPP.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
