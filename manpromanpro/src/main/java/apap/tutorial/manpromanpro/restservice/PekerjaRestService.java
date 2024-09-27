package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;

import java.util.List;

public interface PekerjaRestService {
    PekerjaResponseDTO addPekerja(AddPekerjaRequestDTO pekerjaDTO);
    List<PekerjaResponseDTO> getAllPekerja();
    PekerjaResponseDTO getPekerjaById(Long idPekerja);
    PekerjaResponseDTO updatePekerjaRest(UpdatePekerjaRequestRestDTO pekerjaDTO);
}

