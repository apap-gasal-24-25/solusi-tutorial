package apap.tutorial.manpromanpro.dto.response;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyekResponseDTO {
    private UUID id;
    private String nama;
    private String deskripsi;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private DeveloperResponseDTO developer;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
