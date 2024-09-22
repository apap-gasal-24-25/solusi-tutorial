package apap.tutorial.manpromanpro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.response.PekerjaResponseDTO;
import apap.tutorial.manpromanpro.model.Pekerja;

@Service
public class PekerjaServiceImpl implements PekerjaService {
    
    private final WebClient webClient;

    public PekerjaServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                            .baseUrl("http://localhost:8080/api")
                            .build();
    }

    @Override
    public Pekerja addPekerja(AddPekerjaRequestDTO pekerjaDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPekerja'");
    }

    @Override
    public List<PekerjaResponseDTO> getAllPekerja() {
        return webClient
                .get()
                .uri("/pekerja/viewall")
                .retrieve()
                .bodyToFlux(PekerjaResponseDTO.class)
                .collectList()
                .block();
    }

    @Override
    public PekerjaResponseDTO getPekerjaById(Long idPekerja) {
        return webClient
                .get()
                .uri("/pekerja/" + idPekerja)
                .retrieve()
                .bodyToMono(PekerjaResponseDTO.class)
                .block();
    }
    
}
