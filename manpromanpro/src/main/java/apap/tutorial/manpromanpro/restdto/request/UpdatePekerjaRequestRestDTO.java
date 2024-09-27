package apap.tutorial.manpromanpro.restdto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePekerjaRequestRestDTO extends AddPekerjaRequestRestDTO {
    @NotNull(message = "ID pekerja tidak boleh kosong")
    private Long id;
}
