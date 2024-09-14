package apap.tutorial.manpromanpro.repository;

import apap.tutorial.manpromanpro.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ProyekDb extends JpaRepository<Proyek, UUID> {
    List<Proyek> findAllByOrderByNama();   

    List<Proyek> findByNamaContainsIgnoreCaseOrderByNama(String nama);  
    
    List<Proyek> findByStatusOrderByNama(String status);  

    List<Proyek> findByNamaContainsIgnoreCaseAndStatusOrderByNama(String nama, String status); 


}
