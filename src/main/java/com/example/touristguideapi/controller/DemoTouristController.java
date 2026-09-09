package com.example.touristguideapi.controller;

public class DemoTouristController {
/*    package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("idag", "tirsdag");
        return ResponseEntity.ok(attractions);
        //Ændre returntypen/statement til string eller
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        return touristAttraction == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(touristAttraction);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction);
        return touristAttraction == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.status(HttpStatus.CREATED).body(touristAttraction);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<TouristAttraction> updateAttraction(@RequestBody String name, TouristAttraction touristAttraction) {
        touristService.updateAttraction(name, touristAttraction);
        return touristAttraction == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.status(HttpStatus.CREATED).body(touristAttraction);
    }

    @PostMapping("/delete/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> deleteAttraction(@PathVariable String name, TouristAttraction touristAttraction) {
        touristService.deleteAttraction(name);
        return name == null ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.status(HttpStatus.CREATED).body(touristAttraction);
    }
}

    /*  @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        TouristAttraction touristAttraction = touristService.getAttractionByName(name);
        return touristAttraction == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(touristAttraction);
    } */
}
