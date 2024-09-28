package apap.tutorial.manpromanpro.restdto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProyekRequestRestDTO {
    @NotNull(message = "Nama proyek tidak boleh kosong")
    @Size(max = 50, message = "Nama proyek maksimal 50 karakter")
    private String nama;

    @NotEmpty(message = "Deskripsi proyek tidak boleh kosong")
    private String deskripsi;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Tanggal mulai proyek maksimal adalah hari ini")
    private Date tanggalMulai;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Tanggal selesai pro yek minimal adalah hari ini")
    private Date tanggalSelesai;

    @NotEmpty(message = "Status proyek tidak boleh kosong")
    private String status;

    @NotNull(message = "Developer proyek tidak boleh kosong")
    private Long developer;

    private List<Long> listPekerja;
}
