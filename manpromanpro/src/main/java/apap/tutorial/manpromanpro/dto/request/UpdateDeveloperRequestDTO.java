package apap.tutorial.manpromanpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDeveloperRequestDTO extends AddDeveloperRequestDTO {
    private Long id;
}
