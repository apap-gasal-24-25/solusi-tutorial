package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ProyekDb extends JpaRepository<Proyek, UUID> {
    List<Proyek> findAll(Sort sort);

    List<Proyek> findByNamaContainingIgnoreCase(String nama, Sort sort);
    
    List<Proyek> findByStatus(String status, Sort sort);

    List<Proyek> findByNamaContainingIgnoreCaseAndStatus(String nama, String status, Sort sort);
}
