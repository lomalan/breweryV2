package com.lomalan.breweryv2.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBeer() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBeerByUpc() {
    }

    @Test
    void getAllBeers() {
    }

    @Test
    void saveBeer() throws Exception {
        BeerDto build = BeerDto.builder()
                .beerName("Name").price(BigDecimal.TEN)
                .style(BeerStyleEnum.ALE)
                .upc(11L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(build);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto build = BeerDto.builder()
                .beerName("Name").style(BeerStyleEnum.ALE).price(BigDecimal.TEN).
                        upc(11L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(build);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"  + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}