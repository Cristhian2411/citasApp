package citasAPP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import citasAPP.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
