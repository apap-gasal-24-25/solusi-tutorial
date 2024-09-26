package apap.tutorial.manpromanpro.service;

import java.util.List;

import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.model.Pekerja;


public interface PekerjaService {
    List<PekerjaResponseDTO> getAllPekerjaFromRest();
    PekerjaResponseDTO getPekerjaByIdFromRest(Long idPekerja);
    Pekerja addPekerja(Pekerja pekerja);
    List<Pekerja> getAllPekerja();
    void deleteListPekerja(List<Pekerja> listPekerja);
}
