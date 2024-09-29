package apap.tutorial.manpromanpro.restdto.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggalBerdiri;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProyekResponseDTO> listProyek;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Jakarta")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Jakarta")
    private Date updatedAt;
}
