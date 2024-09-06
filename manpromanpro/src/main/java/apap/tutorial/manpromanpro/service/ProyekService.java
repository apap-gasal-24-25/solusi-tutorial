package apap.tutorial.manpromanpro.service;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.model.Proyek;

public interface ProyekService {
    // Method untuk menambahkan proyek
    void createProyek(Proyek proyek);

    // Method untuk mendapatkan semua proyek yang telah tersimpan
    List<Proyek> getAllProyek();

    // Method untuk mendapatkan proyek berdasarkan id
    Proyek getProyekById(UUID id);
}
