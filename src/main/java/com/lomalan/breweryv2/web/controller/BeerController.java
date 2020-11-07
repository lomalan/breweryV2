package com.lomalan.breweryv2.web.controller;

import com.lomalan.breweryv2.services.BeerService;
import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import com.lomalan.breweryv2.web.model.pageable.BeerPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerPagedList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);
    }
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable @NotNull UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable Long upc) {

        return new ResponseEntity<>(beerService.getBeerByUpc(upc), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BeerPagedList> getAllBeers() {
        return null;
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveBeer(@Validated @RequestBody BeerDto beerDto) {

        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

}
