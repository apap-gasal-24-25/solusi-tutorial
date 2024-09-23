package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.dto.request.AddProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Proyek;

import java.util.UUID;

public interface ProyekRestService {
    Proyek addProyek(AddProyekRequestDTO addProyekRequestDTO);
    Proyek getProyekById(UUID proyekId);
    Proyek updateProyek(UpdateProyekRequestDTO updateProyekRequestDTO);
    Proyek deleteProyek(UUID proyekId);
}
