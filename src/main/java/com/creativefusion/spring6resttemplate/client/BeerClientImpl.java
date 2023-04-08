package com.creativefusion.spring6resttemplate.client;

import com.creativefusion.spring6resttemplate.model.BeerDTO;
import com.creativefusion.spring6resttemplate.model.BeerDTOPageImpl;
import com.creativefusion.spring6resttemplate.model.BeerStyle;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author sm@creativefusion.net
 */
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {

    private static final String GET_BEER_PATH = "/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers() {
        return this.listBeers(null, null, null, null, null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
                                   Integer pageSize) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName != null) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        if (beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }

        if (showInventory != null) {
            uriComponentsBuilder.queryParam("showInventory", beerStyle);
        }

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", beerStyle);
        }

        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", beerStyle);
        }

        ResponseEntity<String> stringResponse =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString() , String.class);

        ResponseEntity<Map> mapResponse =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Map.class);

        System.out.println(mapResponse.getBody());

        ResponseEntity<JsonNode> jsonResponse =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(), JsonNode.class);

        jsonResponse.getBody().findPath("content")
                .elements().forEachRemaining(node -> {
                    System.out.println(node.get("beerName").asText());
                });

        ResponseEntity<BeerDTOPageImpl> dtoPageResponse =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString() , BeerDTOPageImpl.class);

        return dtoPageResponse.getBody();
    }
}
