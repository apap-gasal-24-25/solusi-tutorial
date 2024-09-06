package apap.tutorial.manpromanpro.model;

import java.util.UUID;

public class Proyek {
    private UUID id;
    private String nama;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status;
    private String developer;
    

    public Proyek(UUID id, String nama, String tanggalMulai, String tanggalSelesai, String status,
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
    public String getTanggalMulai() {
        return tanggalMulai;
    }
    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }
    public String getTanggalSelesai() {
        return tanggalSelesai;
    }
    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDeveloper() {
        return developer;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
