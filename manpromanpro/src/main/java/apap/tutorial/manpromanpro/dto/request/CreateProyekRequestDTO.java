package apap.tutorial.manpromanpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProyekRequestDTO {
    private String nama;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private String status;
}

