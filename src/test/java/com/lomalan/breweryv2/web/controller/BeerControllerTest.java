package com.lomalan.breweryv2.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lomalan.breweryv2.services.BeerService;
import com.lomalan.breweryv2.web.model.BeerDto;
import com.lomalan.breweryv2.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BeerService beerService ;

    @Test
    void getBeer() throws Exception{
        given(beerService.getById(any())).willReturn(new BeerDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBeerByUpc() throws Exception{

        given(beerService.getBeerByUpc(any())).willReturn(new BeerDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/beerUpc/11111")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        BeerDto build = BeerDto.builder()
                .beerName("Name").price(BigDecimal.TEN)
                .beerStyle(BeerStyleEnum.ALE)
                .upc(11L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(build);
        given(beerService.saveNewBeer(any())).willReturn(build);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto build = BeerDto.builder()
                .beerName("Name").beerStyle(BeerStyleEnum.ALE).price(BigDecimal.TEN).
                        upc(11L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(build);
        given(beerService.updateBeer(any(), any())).willReturn(build);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"  + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}