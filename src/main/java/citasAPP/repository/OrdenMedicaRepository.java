package citasAPP.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import citasAPP.entity.OrdenMedica;

public interface OrdenMedicaRepository extends JpaRepository<OrdenMedica, Long> {
}

