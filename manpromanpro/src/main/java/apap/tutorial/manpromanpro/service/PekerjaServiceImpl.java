package apap.tutorial.manpromanpro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import apap.tutorial.manpromanpro.dto.response.PekerjaResponseDTO;

@Service
public class PekerjaServiceImpl implements PekerjaService {
    
    private final WebClient webClient;

    public PekerjaServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                            .baseUrl("http://localhost:8080/api")
                            .build();
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
                .uri("/pekerja?id=" + idPekerja)
                .retrieve()
                .bodyToMono(PekerjaResponseDTO.class)
                .block();
    }
    
}
