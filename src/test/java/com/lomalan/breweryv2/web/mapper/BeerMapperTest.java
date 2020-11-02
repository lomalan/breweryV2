package com.lomalan.breweryv2.web.mapper;

import com.lomalan.breweryv2.domain.Beer;
import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BeerMapperTest {
    @Autowired
    private BeerMapper testObject;

    @Test
    void mapBeerDtoToBeer() {
        BeerDto beerDto = BeerDto.builder().beerName("Name").upc(111L).price(BigDecimal.TEN).style(BeerStyleEnum.ALE).build();
        Beer beer = testObject.beerDtoToBeer(beerDto);

        assertNotNull(beer);
        assertNotNull(beer.getBeerName());
        assertEquals(beerDto.getBeerName(), beer.getBeerName());
        assertEquals(beerDto.getUpc(), beer.getUpc());
    }
}
