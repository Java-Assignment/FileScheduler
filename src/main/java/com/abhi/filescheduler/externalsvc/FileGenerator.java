package com.abhi.filescheduler.externalsvc;

import com.abhi.filescheduler.dto.FileConfigDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class FileGenerator {
    @Autowired
    private WebClient.Builder webClientBuilder;

    /* This code is used to send  File info to file generator so that
    we save all the accounts details from Account-generator into file name
    send by the scheduler to the file generator */

    private URI uri;

    public FileGenerator() {
        uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9009/files").build().toUri();
    }

    public FileConfigDTO callFileGenerationService(FileConfigDTO fileConfigDTO)
            throws JsonProcessingException {
        WebClient webClient = webClientBuilder.build();
        Mono<FileConfigDTO> mono = webClient.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ObjectMapper().writeValueAsString(fileConfigDTO))
                .exchangeToMono(
                        response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToMono(FileConfigDTO.class);
                            } else {
                                System.out.println("Exception in creating file:"+fileConfigDTO);
                                return response.createException().flatMap(Mono::error);
                            }
                        }
                );

        FileConfigDTO fileConfigDTO1 = mono.block();
        log.info("the full file details are :" + fileConfigDTO1);
        return fileConfigDTO1;
    }
}
