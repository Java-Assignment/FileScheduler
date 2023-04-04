package com.abhi.filescheduler.externalsvc;

import com.abhi.filescheduler.dto.FileDTO;
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
/* This code is used to send  File info to file generator so that  we save all the accounts details from Accountgenerator into file name send by the scheduler to the file generator */
    private URI uri;
    public FineNameGenerator() {
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9009/file").build().toUri();
    }

    public FileDTO sendFile(FileDTO fileDTO) throws JsonProcessingException {
        WebClient webClient = webClientBuilder.build();
        Mono<FileDTO> mono =webClient.post()
                .bodyValue(new ObjectMapper().writeValueAsString(fileDTO))
                .exchangeToMono(
                        response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToMono(FileDTO.class);
                            } else {
                                return response.createException().flatMap(Mono::error);
                            }
                        }
                );

        FileDTO fileDTO1 = mono.block();
        log.info("the full file details are :" + fileDTO1);
        return fileDTO1;
    }
    }
