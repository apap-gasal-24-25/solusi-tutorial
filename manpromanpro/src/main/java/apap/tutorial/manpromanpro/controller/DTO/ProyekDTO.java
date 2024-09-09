package apap.tutorial.manpromanpro.controller.DTO;

import java.sql.Date;
import java.util.UUID;


public class ProyekDTO {
    private UUID id;
    private String nama;
    private String deskripsi;
    private String klien;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private Integer status;
    private String developer;

    public ProyekDTO(){}

    public ProyekDTO(UUID id, String nama, String deskripsi, String klien, Date tanggalMulai, Date tanggalSelesai, Integer status, String developer) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.klien = klien;
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

    
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKlien() {
        return klien;
    }

    public void setKlien(String klien) {
        this.klien = klien;
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
