package apap.tutorial.manpromanpro.model;

import java.sql.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class Proyek {

    public enum StatusProyek{
        PERENCANAAN,
        EKSEKUSI,
        SELESAI,
        DITUNDA,
        BATAL;
    }

    private UUID id;
    private String nama;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalMulai;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalSelesai;
    private Integer status;
    private String developer;
    

    public Proyek(UUID id, String nama, Date tanggalMulai, Date tanggalSelesai, Integer status,
            String developer) {
        this.id = id;
        this.nama = nama;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.status = status;
        this.developer = developer;
    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }
    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }
    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }
    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDeveloper() {
        return developer;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
