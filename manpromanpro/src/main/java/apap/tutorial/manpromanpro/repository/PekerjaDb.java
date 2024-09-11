package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Pekerja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PekerjaDb extends JpaRepository<Pekerja, Long> {
}
