package apap.tutorial.manpromanpro.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeveloperResponseDTO {
    private Long id;
    private String nama;
    private String alamat;
    private Date tanggalBerdiri;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
