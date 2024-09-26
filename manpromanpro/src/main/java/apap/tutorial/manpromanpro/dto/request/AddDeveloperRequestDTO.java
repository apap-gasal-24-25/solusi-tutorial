package apap.tutorial.manpromanpro.dto.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

