package com.lomalan.breweryv2.web.mapper;

import com.lomalan.breweryv2.domain.Beer;
import com.lomalan.breweryv2.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
