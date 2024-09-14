package apap.tutorial.manpromanpro.service;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.repository.ProyekDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.manpromanpro.model.Proyek;

@Service
public class ProyekServiceImpl implements ProyekService {
    @Autowired
    ProyekDb proyekDb;

    @Override
    public Proyek addProyek(Proyek proyek) {
        return proyekDb.save(proyek);
    }

    @Override
    public List<Proyek> getAllProyek() {
        return proyekDb.findAllByOrderByNama();
    }

    @Override
    public Proyek getProyekById(UUID idProyek) {
        for (Proyek proyek : getAllProyek()) {
            if (proyek.getId().equals(idProyek)) {
                return proyek;
            }
        }
        return null;
    }

    @Override
    public Proyek updateProyek(Proyek proyek) {
        Proyek getProyek = getProyekById(proyek.getId());
        if (getProyek != null) {
            getProyek.setNama(proyek.getNama());
            getProyek.setDeskripsi(proyek.getDeskripsi());
            getProyek.setTanggalMulai(proyek.getTanggalMulai());
            getProyek.setTanggalSelesai(proyek.getTanggalSelesai());
            getProyek.setStatus(proyek.getStatus());
            getProyek.setDeveloper(proyek.getDeveloper());
            proyekDb.save(getProyek);

            return getProyek;
        }

        return null;
    }

    @Override
    public void deleteProyek(Proyek proyek) {
        proyekDb.delete(proyek);
    }

    @Override
    public List<Proyek> getProyekByNama(String nama) {
        return proyekDb.findByNamaContainsIgnoreCaseOrderByNama(nama);
    }

    @Override
    public List<Proyek> getProyekByStatus(String status) {
        return proyekDb.findByStatusOrderByNama(status);
    }

    @Override
    public List<Proyek> getProyekByNamaAndStatus(String nama, String status) {
       return proyekDb.findByNamaContainsIgnoreCaseAndStatusOrderByNama(nama, status);
    }

}
