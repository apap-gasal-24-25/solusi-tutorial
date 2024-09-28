package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;

import java.util.List;

public interface PekerjaRestService {
    PekerjaResponseDTO addPekerja(AddPekerjaRequestRestDTO pekerjaDTO);
    List<PekerjaResponseDTO> getAllPekerja();
    PekerjaResponseDTO getPekerjaById(Long idPekerja);
    PekerjaResponseDTO updatePekerjaRest(UpdatePekerjaRequestRestDTO pekerjaDTO);
}

