package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.restdto.response.DeveloperResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.ProyekResponseDTO;
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
    public PekerjaResponseDTO addPekerja(AddPekerjaRequestDTO pekerjaDTO) {
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

        var newPekerja = pekerjaDb.save(pekerja);
        return getPekerjaById(newPekerja.getId());
    }

    @Override
    public List<PekerjaResponseDTO> getAllPekerja() {

        var listPekerja = pekerjaDb.findAll();
        var listPekerjaResponseDTO = new ArrayList<PekerjaResponseDTO>();
        listPekerja.forEach(pekerja -> {
            var pekerjaResponseDTO = new PekerjaResponseDTO();
            pekerjaResponseDTO.setId(pekerja.getId());
            pekerjaResponseDTO.setNama(pekerja.getNama());
            pekerjaResponseDTO.setUsia(pekerja.getUsia());
            pekerjaResponseDTO.setPekerjaan(pekerja.getPekerjaan());
            pekerjaResponseDTO.setBiografi(pekerja.getBiografi());
            pekerjaResponseDTO.setCreatedAt(pekerja.getCreatedAt());
            pekerjaResponseDTO.setUpdatedAt(pekerja.getUpdatedAt());

            if (pekerja.getListProyek() != null) {
                var listProyekResponseDTO = new ArrayList<ProyekResponseDTO>();
                pekerja.getListProyek().forEach(proyek -> {
                    var proyekResponseDTO = new ProyekResponseDTO();
                    var developerResponseDTO = new DeveloperResponseDTO();

                    proyekResponseDTO.setId(proyek.getId());
                    proyekResponseDTO.setNama(proyek.getNama());
                    proyekResponseDTO.setDeskripsi(proyek.getDeskripsi());
                    proyekResponseDTO.setTanggalMulai(proyek.getTanggalMulai());
                    proyekResponseDTO.setTanggalSelesai(proyek.getTanggalSelesai());
                    proyekResponseDTO.setStatus(proyek.getStatus());
                    proyekResponseDTO.setCreatedAt(proyek.getCreatedAt());
                    proyekResponseDTO.setUpdatedAt(proyek.getUpdatedAt());

                    developerResponseDTO.setId(proyek.getDeveloper().getId());
                    developerResponseDTO.setNama(proyek.getDeveloper().getNama());
                    developerResponseDTO.setAlamat(proyek.getDeveloper().getAlamat());
                    developerResponseDTO.setTanggalBerdiri(proyek.getDeveloper().getTanggalBerdiri());
                    developerResponseDTO.setEmail(proyek.getDeveloper().getEmail());
                    developerResponseDTO.setCreatedAt(proyek.getDeveloper().getCreatedAt());
                    developerResponseDTO.setUpdatedAt(proyek.getDeveloper().getUpdatedAt());
                    proyekResponseDTO.setDeveloper(developerResponseDTO);

                    listProyekResponseDTO.add(proyekResponseDTO);
                });

                pekerjaResponseDTO.setListProyek(listProyekResponseDTO);
            }

            listPekerjaResponseDTO.add(pekerjaResponseDTO);
        });

        return listPekerjaResponseDTO;
    }

    @Override
    public PekerjaResponseDTO getPekerjaById(Long idPekerja) {
        var getPekerja = pekerjaDb.findById(idPekerja).orElse(null);

        if (getPekerja == null) {
            return null;
        }

        var pekerjaResponseDTO = new PekerjaResponseDTO();
        pekerjaResponseDTO.setId(getPekerja.getId());
        pekerjaResponseDTO.setNama(getPekerja.getNama());
        pekerjaResponseDTO.setUsia(getPekerja.getUsia());
        pekerjaResponseDTO.setPekerjaan(getPekerja.getPekerjaan());
        pekerjaResponseDTO.setBiografi(getPekerja.getBiografi());
        pekerjaResponseDTO.setCreatedAt(getPekerja.getCreatedAt());
        pekerjaResponseDTO.setUpdatedAt(getPekerja.getUpdatedAt());

        if (getPekerja.getListProyek() != null) {
            var listProyekResponseDTO = new ArrayList<ProyekResponseDTO>();
            getPekerja.getListProyek().forEach(proyek -> {
                var proyekResponseDTO = new ProyekResponseDTO();
                var developerResponseDTO = new DeveloperResponseDTO();

                proyekResponseDTO.setId(proyek.getId());
                proyekResponseDTO.setNama(proyek.getNama());
                proyekResponseDTO.setDeskripsi(proyek.getDeskripsi());
                proyekResponseDTO.setTanggalMulai(proyek.getTanggalMulai());
                proyekResponseDTO.setTanggalSelesai(proyek.getTanggalSelesai());
                proyekResponseDTO.setStatus(proyek.getStatus());
                proyekResponseDTO.setCreatedAt(proyek.getCreatedAt());
                proyekResponseDTO.setUpdatedAt(proyek.getUpdatedAt());

                developerResponseDTO.setId(proyek.getDeveloper().getId());
                developerResponseDTO.setNama(proyek.getDeveloper().getNama());
                developerResponseDTO.setAlamat(proyek.getDeveloper().getAlamat());
                developerResponseDTO.setTanggalBerdiri(proyek.getDeveloper().getTanggalBerdiri());
                developerResponseDTO.setEmail(proyek.getDeveloper().getEmail());
                developerResponseDTO.setCreatedAt(proyek.getDeveloper().getCreatedAt());
                developerResponseDTO.setUpdatedAt(proyek.getDeveloper().getUpdatedAt());
                proyekResponseDTO.setDeveloper(developerResponseDTO);

                listProyekResponseDTO.add(proyekResponseDTO);
            });

            pekerjaResponseDTO.setListProyek(listProyekResponseDTO);
        }

        return pekerjaResponseDTO;
    }

    @Override
    public PekerjaResponseDTO updatePekerja(UpdatePekerjaRequestDTO pekerjaDTO) {
        Pekerja pekerja = pekerjaDb.findById(pekerjaDTO.getId()).orElse(null);
        if (pekerja == null) {
            return null;
        }

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

        var updatePekerja = pekerjaDb.save(pekerja);
        return getPekerjaById(updatePekerja.getId());
    }
}

