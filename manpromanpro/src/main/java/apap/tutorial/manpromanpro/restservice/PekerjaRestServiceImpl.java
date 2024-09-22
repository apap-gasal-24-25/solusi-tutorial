package apap.tutorial.manpromanpro.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdatePekerjaRequestDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import apap.tutorial.manpromanpro.repository.ProyekDb;

import java.util.List;
import java.util.ArrayList;

@Service
public class PekerjaRestServiceImpl implements PekerjaRestService {
    @Autowired
    PekerjaDb pekerjaDb;

    @Autowired
    ProyekDb proyekDb;

    @Override
    public Pekerja addPekerja(AddPekerjaRequestDTO pekerjaDTO) {
        Pekerja pekerja = new Pekerja();
        pekerja.setNama(pekerjaDTO.getNama());
        pekerja.setUsia(pekerjaDTO.getUsia());
        pekerja.setPekerjaan(pekerjaDTO.getPekerjaan());

        if (pekerjaDTO.getBiografi() != null) {
            pekerja.setBiografi(pekerjaDTO.getBiografi());
        }

        pekerja.setListProyek(new ArrayList<>());
        if (pekerjaDTO.getListProyek() != null) {
            pekerjaDTO.getListProyek().forEach(idProyek -> {
                Proyek proyek = proyekDb.findById(idProyek).orElse(null);
                if (proyek != null) {
                    // Add the Proyek to the Pekerja's listProyek
                    pekerja.getListProyek().add(proyek);
                    
                    // Add the Pekerja to the Proyek's listPekerja
                    proyek.getListPekerja().add(pekerja);
                }
            });
        }

        return pekerjaDb.save(pekerja);
    }

    @Override
    public List<Pekerja> getAllPekerja() {
        return pekerjaDb.findAll();
    }

    @Override
    public Pekerja getPekerjaById(Long idPekerja) {
        return pekerjaDb.findById(idPekerja).orElse(null);
    }

    @Override
    public Pekerja updatePekerja(UpdatePekerjaRequestDTO pekerjaDTO) {
        Pekerja pekerja = pekerjaDb.findById(pekerjaDTO.getId()).orElse(null);
        pekerja.setNama(pekerjaDTO.getNama());
        pekerja.setUsia(pekerjaDTO.getUsia());
        pekerja.setPekerjaan(pekerjaDTO.getPekerjaan());

        if (pekerjaDTO.getBiografi() != null) {
            pekerja.setBiografi(pekerjaDTO.getBiografi());
        }

        if (pekerjaDTO.getListProyek() != null) {
            pekerja.setListProyek(new ArrayList<>());
            pekerjaDTO.getListProyek().forEach(idProyek -> {
                Proyek proyek = proyekDb.findById(idProyek).orElse(null);
                if (proyek != null) {
                    // Add the Proyek to the Pekerja's listProyek
                    pekerja.getListProyek().add(proyek);
                    
                    // Add the Pekerja to the Proyek's listPekerja
                    if (!proyek.getListPekerja().contains(pekerja)) {
                        proyek.getListPekerja().add(pekerja);
                    }
                }
            });
        }
        return pekerjaDb.save(pekerja);
    }

    @Override
    public void deletePekerja(Pekerja pekerja) {
        pekerjaDb.delete(pekerja);
    }
}

