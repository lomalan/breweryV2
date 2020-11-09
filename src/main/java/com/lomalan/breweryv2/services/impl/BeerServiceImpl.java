package com.lomalan.breweryv2.services.impl;

import com.lomalan.breweryv2.dao.BeerDao;
import com.lomalan.breweryv2.domain.Beer;
import com.lomalan.breweryv2.services.BeerService;
import com.lomalan.breweryv2.web.mapper.BeerMapper;
import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import com.lomalan.breweryv2.web.model.pageable.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerDao beerDao;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId")
    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerDao.findById(beerId).orElseThrow(() -> new IllegalArgumentException("Beer not found")));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerDao.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerDao.findById(beerId).orElseThrow(() -> new IllegalArgumentException("Beer not found"));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerDao.save(beer));
    }

    @Cacheable(cacheNames = "beerListCache")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerDao.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerDao.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerDao.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerDao.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());

        return beerPagedList;
    }


    @Cacheable(cacheNames = "beerCache", key = "#upc")
    @Override
    public BeerDto getBeerByUpc(Long upc) {
        return beerMapper.beerToBeerDto(beerDao.findByUpc(upc));
    }
}
