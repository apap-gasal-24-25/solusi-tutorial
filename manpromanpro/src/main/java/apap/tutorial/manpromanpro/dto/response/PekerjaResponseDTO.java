package apap.tutorial.manpromanpro.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PekerjaResponseDTO {
    private Long id;
    private String nama;
    private int usia;
    private String pekerjaan;
    private String biografi;
    private List<ProyekResponseDTO> listProyek;
}
