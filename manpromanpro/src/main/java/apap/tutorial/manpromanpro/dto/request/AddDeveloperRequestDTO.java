package apap.tutorial.manpromanpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddDeveloperRequestDTO {
    private String nama;
    private String alamat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalBerdiri;
    private String email;
}

