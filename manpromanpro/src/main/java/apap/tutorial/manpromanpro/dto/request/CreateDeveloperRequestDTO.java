package apap.tutorial.manpromanpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateDeveloperRequestDTO {
    private String nama;
    private String alamat;
    private Date tanggalBerdiri;
    private String email;
}

