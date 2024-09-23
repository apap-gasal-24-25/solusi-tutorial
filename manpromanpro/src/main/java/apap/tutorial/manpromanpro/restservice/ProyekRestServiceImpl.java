package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.dto.request.AddProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.repository.ProyekDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProyekRestServiceImpl implements ProyekRestService {

    @Autowired
    ProyekDb proyekDb;

    @Override
    public Proyek addProyek(AddProyekRequestDTO addProyekRequestDTO) {
        Proyek proyek = new Proyek();
        proyek.setNama(addProyekRequestDTO.getNama());
        proyek.setDeskripsi(addProyekRequestDTO.getDeskripsi());
        proyek.setTanggalMulai(addProyekRequestDTO.getTanggalMulai());
        proyek.setTanggalSelesai(addProyekRequestDTO.getTanggalSelesai());
        proyek.setStatus(addProyekRequestDTO.getStatus());
        proyek.setDeveloper(addProyekRequestDTO.getDeveloper());
        return proyekDb.save(proyek);
    }

    @Override
    public Proyek getProyekById(UUID proyekId) {
        return proyekDb.findById(proyekId).get();
    }

    @Override
    public Proyek updateProyek(UpdateProyekRequestDTO updateProyekRequestDTO) {
        Proyek getProyek = getProyekById(updateProyekRequestDTO.getId());
        if (getProyek != null) {
            getProyek.setNama(updateProyekRequestDTO.getNama());
            getProyek.setDeskripsi(updateProyekRequestDTO.getDeskripsi());
            getProyek.setTanggalMulai(updateProyekRequestDTO.getTanggalMulai());
            getProyek.setTanggalSelesai(updateProyekRequestDTO.getTanggalSelesai());
            getProyek.setStatus(updateProyekRequestDTO.getStatus());
            getProyek.setDeveloper(updateProyekRequestDTO.getDeveloper());
            proyekDb.save(getProyek);

            return getProyek;
        }

        return null;
    }

    @Override
    public Proyek deleteProyek(UUID proyekId) {
        Proyek getProyek = getProyekById(proyekId);
        if (getProyek != null) {
            proyekDb.delete(getProyek);
            return getProyek;
        }
        return null;
    }
}
