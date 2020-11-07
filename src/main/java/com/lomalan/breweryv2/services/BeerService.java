package com.lomalan.breweryv2.services;

import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import com.lomalan.breweryv2.web.model.pageable.BeerPagedList;
import org.springframework.data.domain.PageRequest;
import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of);

    BeerDto getBeerByUpc(Long upc);
}
