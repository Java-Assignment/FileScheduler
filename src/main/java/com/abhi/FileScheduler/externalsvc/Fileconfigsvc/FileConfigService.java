package com.abhi.FileScheduler.externalsvc.Fileconfigsvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
@Slf4j
public class FileConfigService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private URI uri;

    public FileConfigService(){
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9007/file").build().toUri();
    }

    public List<FileDTO> getAllAccounts(){
        WebClient webClient=webClientBuilder.build();

        List<FileDTO> accountDTOList = webClient.get()
                .exchangeToFlux(
                        response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToFlux(FileDTO.class);
                            } else {
                                return response.createException().flatMapMany(Mono::error);
                            }
                        }
                )
                .collectList()
                .block();
        log.info("AccountSvcWebClient GET AC size:"+accountDTOList.size());
        return accountDTOList;
    }
}
