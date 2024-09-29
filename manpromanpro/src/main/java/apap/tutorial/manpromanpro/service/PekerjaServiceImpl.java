package apap.tutorial.manpromanpro.service;

import java.util.ArrayList;
import java.util.List;

import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;

import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import jakarta.transaction.Transactional;

@Service
public class PekerjaServiceImpl implements PekerjaService {

    @Autowired
    PekerjaDb pekerjaDb;

    private final WebClient webClient;

    public PekerjaServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8080/api")
                .build();
    }

    @Override
    public List<PekerjaResponseDTO> getAllPekerjaFromRest() throws Exception {
        var response = webClient
                .get()
                .uri("/pekerja/viewall")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<List<PekerjaResponseDTO>>>() {})
                .block();

        if (response == null) {
            throw new Exception("Failed consume API getAllPekerja");
        }

        if (response.getStatus() != 200) {
            throw new Exception(response.getMessage());
        }

        return response.getData();
    }

    @Override
    public PekerjaResponseDTO getPekerjaByIdFromRest(Long idPekerja) throws Exception {
        var response = webClient
                .get()
                .uri("/pekerja?id=" + idPekerja)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<PekerjaResponseDTO>>() {})
                .block();

        if (response == null) {
            throw new Exception("Failed consume API getPekerjaById");
        }

        if (response.getStatus() != 200) {
            throw new Exception(response.getMessage());
        }

        return response.getData();
    }

    @Override
    public PekerjaResponseDTO addPekerjaFromRest(AddPekerjaRequestRestDTO pekerjaDTO) throws Exception {
        var response = webClient
                .post()
                .uri("/pekerja/add")
                .bodyValue(pekerjaDTO)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<PekerjaResponseDTO>>() {})
                .block();

        if (response == null) {
            throw new Exception("Failed consume API addPekerja");
        }

        if (response.getStatus() != 201) {
            throw new Exception(response.getMessage());
        }

        return response.getData();
    }

    @Override
    public Pekerja addPekerja(Pekerja pekerja) {
        return pekerjaDb.save(pekerja);
    }

    @Override
    public List<Pekerja> getAllPekerja() {
        return pekerjaDb.findAll(Sort.by(Sort.Order.asc("nama").ignoreCase()));
    }

    @Override
    public void deleteListPekerja(List<Pekerja> listPekerja) {
        var pekerjaToDelete = new ArrayList<Pekerja>();

        if (listPekerja != null) {
            for (Pekerja pekerja : listPekerja) {
                if (pekerja.getListProyek() == null || pekerja.getListProyek().isEmpty()) {
                    pekerjaToDelete.add(pekerja);
                }
            }
        }

        pekerjaDb.deleteAll(pekerjaToDelete);
    }

    @Override
    public PekerjaResponseDTO updatePekerjaFromRest(UpdatePekerjaRequestRestDTO pekerjaDTO) throws Exception {
        var response = webClient
                .put()
                .uri("/pekerja/update")
                .bodyValue(pekerjaDTO)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponseDTO<PekerjaResponseDTO>>() {})
                .block();

        if (response == null) {
            throw new Exception("Failed consume API updatePekerja");
        }

        if (response.getStatus() != 200) {
            throw new Exception(response.getMessage());
        }

        return response.getData();
    }
}
