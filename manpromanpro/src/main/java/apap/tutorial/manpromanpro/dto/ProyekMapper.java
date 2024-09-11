package apap.tutorial.manpromanpro.dto;

import apap.tutorial.manpromanpro.dto.request.CreateProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.model.Proyek;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProyekMapper {
    Proyek createProyekRequestDTOToProyek(CreateProyekRequestDTO createRequestProyekDTO);
    Proyek updateProyekRequestDTOToProyek(UpdateProyekRequestDTO createRequestDeveloperDTO);

    UpdateProyekRequestDTO proyekToUpdateProyekRequestDTO(Proyek proyek);
}
