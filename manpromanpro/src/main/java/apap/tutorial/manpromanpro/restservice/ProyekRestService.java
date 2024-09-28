package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.restdto.request.AddProyekRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdateProyekRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.ProyekResponseDTO;

import java.util.UUID;

public interface ProyekRestService {
    ProyekResponseDTO addProyek(AddProyekRequestRestDTO proyekDTO);
    ProyekResponseDTO getProyekById(UUID idPekerja);
    ProyekResponseDTO updateProyek(UpdateProyekRequestRestDTO ProyekDTO);
    void deleteProyek(UUID idPekerja);
}
