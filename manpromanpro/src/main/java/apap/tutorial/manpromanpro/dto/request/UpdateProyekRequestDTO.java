package apap.tutorial.manpromanpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import jakarta.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProyekRequestDTO extends AddProyekRequestDTO {
    @NotNull(message = "ID proyek tidak boleh kosong")
    private UUID id;
}

