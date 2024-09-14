package apap.tutorial.manpromanpro.service;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.model.Proyek;

public interface ProyekService {
    Proyek addProyek(Proyek proyek);
    List<Proyek> getAllProyek();
    List<Proyek> getProyekByNama(String nama);
    List<Proyek> getProyekByStatus(String status);
    List<Proyek> getProyekByNamaAndStatus(String nama, String status);
    Proyek getProyekById(UUID idProyek);
    Proyek updateProyek(Proyek proyek);
    void deleteProyek(Proyek proyek);
}
