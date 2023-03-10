package com.abhi.FileScheduler.externalsvc;

import com.abhi.FileScheduler.externalsvc.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class FileConfigService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private URI uri;

    public FileConfigService(){
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9007/file").build().toUri();
    }

    public FileDTO getAccountByDate(String date){
        WebClient webClient=webClientBuilder.build();

        FileDTO fileDetails=webClient.get()
                .uri(uri+date)
                .exchangeToMono(
                        response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToMono(FileDTO.class);
                            } else if (response.statusCode().equals(HttpStatus.NOT_FOUND)) {
                                return Mono.empty();
                            } else {
                                return response.createException().flatMap(Mono::error);
                            }
                        }
                )
                .block();
        log.info(" file configDataService getConfigFFileByDate:"+fileDetails);
        return fileDetails;
    }
}
