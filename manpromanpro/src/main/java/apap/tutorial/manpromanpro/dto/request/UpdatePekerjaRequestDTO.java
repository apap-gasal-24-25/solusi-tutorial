package apap.tutorial.manpromanpro.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePekerjaRequestDTO extends AddPekerjaRequestDTO {
    @NotNull(message = "ID pekerja tidak boleh kosong")
    private Long id;
}
