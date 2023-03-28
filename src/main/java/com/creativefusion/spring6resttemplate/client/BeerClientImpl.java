package com.creativefusion.spring6resttemplate.client;

import com.creativefusion.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author sm@creativefusion.net
 */
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> stringResponse =
                restTemplate.getForEntity("http://localhost:8080/api/v1/beer", String.class);

        System.out.println(stringResponse.getBody());

        return null;
    }
}
