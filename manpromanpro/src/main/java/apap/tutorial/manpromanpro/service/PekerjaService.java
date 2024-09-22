package apap.tutorial.manpromanpro.service;

import java.util.List;

import apap.tutorial.manpromanpro.dto.response.PekerjaResponseDTO;

public interface PekerjaService {
    List<PekerjaResponseDTO> getAllPekerja();
    PekerjaResponseDTO getPekerjaById(Long idPekerja);
}
