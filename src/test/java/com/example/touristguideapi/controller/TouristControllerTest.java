package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import com.example.touristguideapi.service.TouristService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
    void getAttractionCityTagsTest() throws Exception{
        final List<String> tivolitags = Arrays.asList(
                "Børnevenlig", "Underholdning");
        when(service.getCities()).thenReturn(tivolitags);
        //{name}/tags")
        mockMvc.perform(post("/tivoli/tags")).andExpect(status().isAccepted())
                .andExpect(view().name("tags"));

        assertEquals(service.getCities(),tivolitags);

    }

    @Test
    void addAttractionTest() throws Exception {
        TouristAttraction ta = new TouristAttraction("Mikkel", "Cool Mofo","KBH", List.of("super-cool","even more cool"), "CoolKid.png");
       // when(service.addAttraction(any(TouristAttraction.class))).thenReturn(ta); Bruges kun på funktioner som ikke er VOID

        mockMvc.perform(post("/attractions/save")
                        .param("name", "Mikkel")
                        .param("description", "Cool Mofo")
                        .param("city", "KBH")
                        .param("tags", "super-cool","even more cool")
                        .param("image", "CoolKid.png"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(service).addAttraction(captor.capture());

        TouristAttraction captorAdding = captor.getValue();

        assertEquals("Mikkel",captorAdding.getName());
        assertEquals("Cool Mofo",captorAdding.getDescription());
        assertEquals("KBH",captorAdding.getCity());
        assertEquals(List.of("super-cool","even more cool"),captorAdding.getTags());
        assertEquals("CoolKid.png",captorAdding.getImage());
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