package com.lomalan.breweryv2.web.controller;

import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.pageable.BeerPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable @NotNull UUID beerId) {
        toBeImpl();
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable Long upc) {
        toBeImpl();
        return null;
    }

    @GetMapping
    public ResponseEntity<BeerPagedList> getAllBeers() {
        toBeImpl();
        return null;
    }

    @PostMapping
    public ResponseEntity saveBeer(@Validated @RequestBody BeerDto beerDto) {
        toBeImpl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/beer/saveBeer/".concat(beerDto.toString()));

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto) {
        toBeImpl();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private void toBeImpl() {
        log.info("To be implemented");
    }
}
