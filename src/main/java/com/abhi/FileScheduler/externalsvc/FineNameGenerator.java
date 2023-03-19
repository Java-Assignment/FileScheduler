package com.abhi.FileScheduler.externalsvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
@Component
@Slf4j
public class FineNameGenerator {
    @Autowired
    private WebClient.Builder webClientBuilder;

    private URI uri;
    public FineNameGenerator() {
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9009/file").build().toUri();
    }

    public String sendFileName(String name) throws JsonProcessingException {
        WebClient webClient = webClientBuilder.build();
        Mono<String> mono =webClient.post()
                .bodyValue(new ObjectMapper().writeValueAsString(name))
                .exchangeToMono(
                        response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToMono(String.class);
                            } else {
                                return response.createException().flatMap(Mono::error);
                            }
                        }
                );

        String filename = mono.block();
        log.info("AccountSvcWebClient. NEW AC :" + filename);
        return filename;
    }
    }
