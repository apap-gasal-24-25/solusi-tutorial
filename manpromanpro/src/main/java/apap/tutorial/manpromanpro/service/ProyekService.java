package apap.tutorial.manpromanpro.service;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.model.Proyek;

public interface ProyekService {
    void createProyek(Proyek proyek);
    List<Proyek> getAllProyek();
    Proyek getProyekById(UUID idProyek);
    Proyek updateProyek(Proyek proyek);
    void deleteProyek(Proyek proyek);
}
