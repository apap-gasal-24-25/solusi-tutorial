package apap.tutorial.manpromanpro.restdto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddPekerjaRequestRestDTO {
    @NotEmpty(message = "Nama pekerja tidak boleh kosong")
    @Size(max = 50, message = "Nama pekerja maksimal 50 karakter")
    private String nama;

    @Positive(message = "Usia pekerja harus merupakan bilangan positif")
    private int usia;

    @NotEmpty(message = "Pekerjaan tidak boleh kosong")
    @Size(max = 30, message = "Pekerjaan maksimal 30 karakter")
    private String pekerjaan;

    private String biografi;

    private List<UUID> listProyek;
}
