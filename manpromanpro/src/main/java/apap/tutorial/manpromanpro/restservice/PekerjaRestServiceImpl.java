package apap.tutorial.manpromanpro.restservice;

import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.DeveloperResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.ProyekResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import apap.tutorial.manpromanpro.repository.ProyekDb;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class PekerjaRestServiceImpl implements PekerjaRestService {
    @Autowired
    PekerjaDb pekerjaDb;

    @Autowired
    ProyekDb proyekDb;

    @Override
    public PekerjaResponseDTO addPekerja(AddPekerjaRequestRestDTO pekerjaDTO) {
        Pekerja pekerja = new Pekerja();
        pekerja.setNama(pekerjaDTO.getNama());
        pekerja.setUsia(pekerjaDTO.getUsia());
        pekerja.setPekerjaan(pekerjaDTO.getPekerjaan());

        if (pekerjaDTO.getBiografi() != null) {
            pekerja.setBiografi(pekerjaDTO.getBiografi());
        }

        pekerja.setListProyek(new ArrayList<>());
        if (pekerjaDTO.getListProyek() != null) {
            var listProyekFromDTO = pekerjaDTO.getListProyek();
            pekerjaDTO.setListProyek(listProyekFromDTO.stream().distinct().toList());

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
        return pekerjaToPekerjaResponseDTO(newPekerja);
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
        var pekerja = pekerjaDb.findById(idPekerja).orElse(null);

        if (pekerja == null) {
            return null;
        }

        return pekerjaToPekerjaResponseDTO(pekerja);
    }

    @Override
    public PekerjaResponseDTO updatePekerjaRest(UpdatePekerjaRequestRestDTO pekerjaDTO) {
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

        var listProyekFromDTO = pekerjaDTO.getListProyek();
        if (listProyekFromDTO != null) {
            listProyekFromDTO = listProyekFromDTO.stream().distinct().toList();

            var listProyekExisting = pekerja.getListProyek();

            // Pekerja sudah memiliki list proyek sebelumnya
            if (listProyekExisting != null && !listProyekExisting.isEmpty()) {

                listProyekExisting.forEach(proyek -> {
                    // Menghapus proyek dari pekerja
                    if (!pekerjaDTO.getListProyek().contains(proyek.getId())) {
                        proyek.getListPekerja().remove(pekerja);
                    }
                });

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
            // Pekerja belum memiliki list proyek sebelumnya
            } else {
                pekerja.setListProyek(new ArrayList<>());
                listProyekFromDTO.forEach(idProyek -> {
                    Proyek proyek = proyekDb.findById(idProyek).orElse(null);
                    if (proyek != null) {
                        // Add the Proyek to the Pekerja's listProyek
                        pekerja.getListProyek().add(proyek);

                        // Add the Pekerja to the Proyek's listPekerja
                        proyek.getListPekerja().add(pekerja);
                    }
                });
            }
        }

        var updatePekerja = pekerjaDb.save(pekerja);
        return pekerjaToPekerjaResponseDTO(updatePekerja);
    }

    @Override
    public void deletePekerja(List<Long> listIdPekerja) throws EntityNotFoundException, ConstraintViolationException {
        var listPekerja = new ArrayList<Pekerja>();

        listIdPekerja.forEach(idPekerja -> {
            var pekerja = pekerjaDb.findById(idPekerja).orElse(null);
            if (pekerja == null) {
                throw new EntityNotFoundException(String.format("Pekerja dengan ID %d tidak ditemukan", idPekerja));
            }

            listPekerja.add(pekerja);
        });

        pekerjaDb.deleteAll(listPekerja);
    }

    private PekerjaResponseDTO pekerjaToPekerjaResponseDTO(Pekerja pekerja) {
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
        return pekerjaResponseDTO;
    }
}
