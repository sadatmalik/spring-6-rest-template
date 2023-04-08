package com.creativefusion.spring6resttemplate.client;

import com.creativefusion.spring6resttemplate.model.BeerDTO;
import com.creativefusion.spring6resttemplate.model.BeerDTOPageImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author sm@creativefusion.net
 */
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String GET_BEER_PATH = "/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> stringResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH , String.class);

        ResponseEntity<Map> mapResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, Map.class);

        System.out.println(mapResponse.getBody());

        ResponseEntity<JsonNode> jsonResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, JsonNode.class);

        jsonResponse.getBody().findPath("content")
                .elements().forEachRemaining(node -> {
                    System.out.println(node.get("beerName").asText());
                });

        ResponseEntity<BeerDTOPageImpl> dtoPageResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH , BeerDTOPageImpl.class);

        return null;
    }
}
