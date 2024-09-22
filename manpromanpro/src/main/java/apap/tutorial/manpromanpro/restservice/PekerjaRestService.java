package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdatePekerjaRequestDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import java.util.List;

public interface PekerjaRestService {
    Pekerja addPekerja(AddPekerjaRequestDTO pekerjaDTO);
    List<Pekerja> getAllPekerja();
    Pekerja getPekerjaById(Long idPekerja);
    Pekerja updatePekerja(UpdatePekerjaRequestDTO pekerjaDTO);
    void deletePekerja(Pekerja pekerja);
}

