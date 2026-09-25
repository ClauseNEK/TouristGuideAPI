package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import com.example.touristguideapi.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService service;

//test for at url give forventede view/string whatevs
    @Test
    void getAttractionsTest() throws Exception {
    mockMvc.perform(get("/attractions")).
            andExpect(status().isOk()).
            andExpect(view().name("attractionList"));
    }

    @Test
    void getAttractionCityTagsTest() {
        final List<String> cities = Arrays.asList(
                "Albertslund", "København", "Odense", "Kongens Lyngby", "Aarhus");
        TouristRepository tr = new TouristRepository();
        assertEquals(tr.getCities(),cities);

    }

    @Test
    void getAllAttrationsTest(){
        when(service.getAllAttractions()).thenReturn(getAttractions());
    }

    public List<TouristAttraction> getAttractions(){
        List<TouristAttraction> list = new ArrayList<>();
        list.add(new TouristAttraction("Tivoli", "Sjov for hele familien", "København",
                List.of("Børnevenlig", "Underholdning"), "tivoli.png"));
        list.add(new TouristAttraction("Bakken", "Underholdning for store og for små", "Kongens Lyngby",
                List.of("Børnevenlig", "Underholdning"), "bakken_logo_2026.png"));
        list.add(new TouristAttraction("Vega", "Musik året rundt", "København",
                List.of("Underholdning"), "vega.png"));
        list.add(new TouristAttraction("Royal Arena", "Koncerter mm.", "København",
                List.of("Underholdning"), "royalarena.png"));
        list.add(new TouristAttraction("TuristInformation", "Hjælp til alle dine turist behov", "København",
                List.of("Gratis"), "turistinformation.png"));
    return list;
    }

}