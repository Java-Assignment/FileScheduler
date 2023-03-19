package com.abhi.FileScheduler.externalsvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@Component
@Slf4j
public class FineNameGenerator {

    private WebClient.Builder webClientBuilder;
    private URI uri;
    public FineNameGenerator() {
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9009/file").build().toUri();
    }

    public String sendFileName(String name) throws JsonProcessingException {
        WebClient webClient = webClientBuilder.build();

        String block = webClient.post()
                .bodyValue(new ObjectMapper().writeValueAsString(name))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("AccountSvcWebClient. NEW AC :" + block);
        return block;
    }
}
