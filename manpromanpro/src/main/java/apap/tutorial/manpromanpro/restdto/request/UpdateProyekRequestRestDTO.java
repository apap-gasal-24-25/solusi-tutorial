package apap.tutorial.manpromanpro.restdto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProyekRequestRestDTO extends AddProyekRequestRestDTO {
    @NotNull(message = "ID proyek tidak boleh kosong")
    private UUID id;
}
