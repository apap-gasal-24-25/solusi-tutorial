package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.restdto.request.AddProyekRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdateProyekRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.DeveloperResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.ProyekResponseDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.repository.DeveloperDb;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import apap.tutorial.manpromanpro.repository.ProyekDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional
public class ProyekRestServiceImpl implements ProyekRestService {

    @Autowired
    ProyekDb proyekDb;

    @Autowired
    DeveloperDb developerDB;

    @Autowired
    PekerjaDb pekerjaDB;

    @Override
    public ProyekResponseDTO addProyek(AddProyekRequestRestDTO proyekDTO) {
        Proyek proyek = new Proyek();
        proyek.setNama(proyekDTO.getNama());
        proyek.setDeskripsi(proyekDTO.getDeskripsi());
        proyek.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyek.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyek.setStatus(proyekDTO.getStatus());

        var developer = developerDB.findById(proyekDTO.getDeveloper()).orElse(null);
        if (developer != null) {
            proyek.setDeveloper(developer);
        }

        proyek.setListPekerja(new ArrayList<>());
        if (proyekDTO.getListPekerja() != null) {
            var listPkerjaFromDTO = proyekDTO.getListPekerja();
            proyekDTO.setListPekerja(listPkerjaFromDTO.stream().distinct().toList());

            proyekDTO.getListPekerja().forEach(idPekerja -> {
                Pekerja pekerja = pekerjaDB.findById(idPekerja).orElse(null);
                if (pekerja != null) {
                    proyek.getListPekerja().add(pekerja);
                    pekerja.getListProyek().add(proyek);
                }
            });
        }

        var newProyek = proyekDb.save(proyek);
        return proyekToProyekResponseDTO(newProyek);
    }

    @Override
    public ProyekResponseDTO getProyekById(UUID idPekerja) {
        var proyek = proyekDb.findById(idPekerja).orElse(null);
        if (proyek == null) {
            return null;
        }

       return proyekToProyekResponseDTO(proyek);
    }

    @Override
    public ProyekResponseDTO updateProyek(UpdateProyekRequestRestDTO proyekDTO) {
        var proyek = proyekDb.findById(proyekDTO.getId()).orElse(null);
        if (proyek == null) {
            return null;
        }

        proyek.setNama(proyekDTO.getNama());
        proyek.setDeskripsi(proyekDTO.getDeskripsi());
        proyek.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyek.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyek.setStatus(proyekDTO.getStatus());

        var developer = developerDB.findById(proyekDTO.getDeveloper()).orElse(null);
        if (developer != null) {
            proyek.setDeveloper(developer);
        }

        if (proyekDTO.getListPekerja() != null) {
            var listPkerjaFromDTO = proyekDTO.getListPekerja();
            proyekDTO.setListPekerja(listPkerjaFromDTO.stream().distinct().toList());

            var listPekerjaExisting = proyek.getListPekerja();

            if (listPekerjaExisting != null && !listPekerjaExisting.isEmpty()) {
                listPekerjaExisting.forEach(pekerja -> {
                    if (!proyekDTO.getListPekerja().contains(pekerja.getId())) {
                        pekerja.getListProyek().remove(proyek);
                    }
                });

                proyek.setListPekerja(new ArrayList<>());
                proyekDTO.getListPekerja().forEach(idPekerja -> {
                    var pekerja = pekerjaDB.findById(idPekerja).orElse(null);
                    if (pekerja != null) {
                        proyek.getListPekerja().add(pekerja);

                        if (!pekerja.getListProyek().contains(proyek)) {
                            pekerja.getListProyek().add(proyek);
                        }
                    }


                });
            } else {
                proyek.setListPekerja(new ArrayList<>());
                proyekDTO.getListPekerja().forEach(idPekerja -> {
                    var pekerja = pekerjaDB.findById(idPekerja).orElse(null);
                    if (pekerja != null) {
                        proyek.getListPekerja().add(pekerja);
                        pekerja.getListProyek().add(proyek);
                    }
                });
            }
        }

        var updateProyek = proyekDb.save(proyek);
        return proyekToProyekResponseDTO(updateProyek);
    }

    @Override
    public void deleteProyek(UUID proyekId) {
        var proyek = proyekDb.findById(proyekId).orElse(null);
        proyekDb.delete(proyek);
    }

    private ProyekResponseDTO proyekToProyekResponseDTO(Proyek proyek) {
        var proyekResponseDTO = new ProyekResponseDTO();
        proyekResponseDTO.setId(proyek.getId());
        proyekResponseDTO.setNama(proyek.getNama());
        proyekResponseDTO.setDeskripsi(proyek.getDeskripsi());
        proyekResponseDTO.setTanggalMulai(proyek.getTanggalMulai());
        proyekResponseDTO.setTanggalSelesai(proyek.getTanggalSelesai());
        proyekResponseDTO.setStatus(proyek.getStatus());
        proyekResponseDTO.setCreatedAt(proyek.getCreatedAt());
        proyekResponseDTO.setUpdatedAt(proyek.getUpdatedAt());

        if (proyek.getDeveloper() != null) {
            var developerResponseDTO = new DeveloperResponseDTO();

            developerResponseDTO.setId(proyek.getDeveloper().getId());
            developerResponseDTO.setNama(proyek.getDeveloper().getNama());
            developerResponseDTO.setAlamat(proyek.getDeveloper().getAlamat());
            developerResponseDTO.setTanggalBerdiri(proyek.getDeveloper().getTanggalBerdiri());
            developerResponseDTO.setEmail(proyek.getDeveloper().getEmail());
            developerResponseDTO.setCreatedAt(proyek.getDeveloper().getCreatedAt());
            developerResponseDTO.setUpdatedAt(proyek.getDeveloper().getUpdatedAt());
            proyekResponseDTO.setDeveloper(developerResponseDTO);
        }

        if (proyek.getListPekerja() != null) {
            var listPekerjaResponseDTO = new ArrayList<PekerjaResponseDTO>();
            for (Pekerja pekerja : proyek.getListPekerja()) {
                var pekerjaResponseDTO = new PekerjaResponseDTO();
                pekerjaResponseDTO.setId(pekerja.getId());
                pekerjaResponseDTO.setNama(pekerja.getNama());
                pekerjaResponseDTO.setUsia(pekerja.getUsia());
                pekerjaResponseDTO.setPekerjaan(pekerja.getPekerjaan());
                pekerjaResponseDTO.setBiografi(pekerja.getBiografi());
                pekerjaResponseDTO.setCreatedAt(pekerja.getCreatedAt());
                pekerjaResponseDTO.setUpdatedAt(pekerja.getUpdatedAt());

                listPekerjaResponseDTO.add(pekerjaResponseDTO);
            }

            proyekResponseDTO.setListPekerja(listPekerjaResponseDTO);
        }

        return proyekResponseDTO;
    }
}
