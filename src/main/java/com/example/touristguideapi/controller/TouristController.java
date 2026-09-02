package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    //alt skal gå igennem service og der skal ikke være repository herinde

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> GetAttraction(@PathVariable String name) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        return touristAttraction == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(touristAttraction);

    }
}