package com.example.touristguideapi.controller;

import com.example.touristguideapi.repository.TouristRepository;
import com.example.touristguideapi.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}