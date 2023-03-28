package com.creativefusion.spring6resttemplate.client;

import com.creativefusion.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

/**
 * @author sm@creativefusion.net
 */
public interface BeerClient {
    Page<BeerDTO> listBeers();
}
