package com.creativefusion.spring6resttemplate.client;

import com.creativefusion.spring6resttemplate.model.BeerDTO;
import com.creativefusion.spring6resttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

/**
 * @author sm@creativefusion.net
 */
public interface BeerClient {
    Page<BeerDTO> listBeers();
    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
                            Integer pageSize);
    BeerDTO getBeerById(UUID beerId);
}
