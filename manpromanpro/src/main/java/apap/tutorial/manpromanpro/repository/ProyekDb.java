package apap.tutorial.manpromanpro.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.manpromanpro.model.Proyek;

@Repository
public interface ProyekDb extends JpaRepository<Proyek, UUID> {
    List<Proyek> findAll(Sort sort);

    List<Proyek> findByNamaContainingIgnoreCase(String nama, Sort sort);
    
    List<Proyek> findByStatus(String status, Sort sort);

    List<Proyek> findByNamaContainingIgnoreCaseAndStatus(String nama, String status, Sort sort);
}
