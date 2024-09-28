package apap.tutorial.manpromanpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.manpromanpro.model.Pekerja;

import java.util.List;
import java.util.UUID;

@Repository
public interface PekerjaDb extends JpaRepository<Pekerja, Long> {
    
    List<Pekerja> findPekerjasByListProyek_Id(UUID proyekId);
}
