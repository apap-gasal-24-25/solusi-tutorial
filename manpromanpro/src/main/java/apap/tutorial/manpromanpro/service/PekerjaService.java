package apap.tutorial.manpromanpro.service;

import java.util.List;
import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.model.Pekerja;

public interface PekerjaService {
    List<PekerjaResponseDTO> getAllPekerjaFromRest() throws Exception;
    PekerjaResponseDTO getPekerjaByIdFromRest(Long idPekerja) throws Exception;
    PekerjaResponseDTO addPekerjaFromRest(AddPekerjaRequestRestDTO pekerjaDTO) throws Exception;
    Pekerja addPekerja(Pekerja pekerja);
    List<Pekerja> getAllPekerja();
    void deleteListPekerja(List<Pekerja> listPekerja);
}

