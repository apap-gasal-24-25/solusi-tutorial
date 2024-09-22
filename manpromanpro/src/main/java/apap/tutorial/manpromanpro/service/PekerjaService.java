package apap.tutorial.manpromanpro.service;

import java.util.List;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.model.Pekerja;

public interface PekerjaService {
    Pekerja addPekerja(AddPekerjaRequestDTO pekerjaDTO);
    List<PekerjaResponseDTO> getAllPekerja();
    PekerjaResponseDTO getPekerjaById(Long idPekerja);
}
