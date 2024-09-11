package apap.tutorial.manpromanpro.dto;

import apap.tutorial.manpromanpro.dto.request.CreateDeveloperRequestDTO;
import apap.tutorial.manpromanpro.model.Developer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    Developer createDeveloperRequestDTOToDeveloper(CreateDeveloperRequestDTO createDeveloperRequestDTO);
}
