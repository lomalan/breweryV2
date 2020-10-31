package com.lomalan.breweryv2.bootstrap;

import com.lomalan.breweryv2.dao.BeerDao;
import com.lomalan.breweryv2.domain.Beer;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

    private final BeerDao beerDao;

    public BeerLoader(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public void run(String... args) {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerDao.count() == 0) {
            beerDao.save(Beer.builder()
                    .style(BeerStyleEnum.ALE)
                    .quantityToBrew(200)
                    .upc(1111111L)
                    .price(BigDecimal.TEN)
                    .build());
        }
    }
}
