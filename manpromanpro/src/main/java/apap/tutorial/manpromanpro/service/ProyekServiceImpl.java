package apap.tutorial.manpromanpro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.Date;

import org.springframework.stereotype.Service;

import apap.tutorial.manpromanpro.model.Proyek;

@Service
public class ProyekServiceImpl implements ProyekService {
    private List<Proyek> listProyek = new ArrayList<Proyek>();

    @Override
    public void createProyek(Proyek proyek) {
        listProyek.add(proyek);
    }

    @Override
    public List<Proyek> getAllProyek() {
        return listProyek;
    }

    @Override
    public Proyek getProyekById(UUID id) {
        for (Proyek proyek : listProyek) {
            if (proyek.getId().equals(id)) {
                return proyek;
            }
        }
        return null;
    }

    @Override
    public void updateProyek(Proyek proyekToUpdate) {
        for (Proyek proyek : listProyek) {
            if (proyek.getId().equals(proyekToUpdate.getId())) {
               
                // Mengganti proyek lama dengan proyek yang sudah diupdate
                listProyek.set(listProyek.indexOf(proyek), proyekToUpdate);
                break;
            }
        }
    }
    
    @Override
    public boolean validateTanggal(Date tanggalMulai, Date tanggalSelesai) {
        if (tanggalMulai.after(tanggalSelesai)) {
            return false;
        }
        return true;
    }
    
    @Override
    public void deleteProyek(UUID id) {
        for (Proyek proyek : listProyek) {
            if (proyek.getId().equals(id)) {
                // Menghapus proyek dari list
                listProyek.remove(proyek);
                break;
            }
        }
    }
}
