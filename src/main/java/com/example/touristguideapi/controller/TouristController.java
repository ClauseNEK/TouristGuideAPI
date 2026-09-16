package com.example.touristguideapi.controller;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    // Viser attractionList.html med data fra repository
    @GetMapping
    public String getAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAllAttractions());
        return "attractionList";
    }

    // Viser tags.html for en enkelt attraktion
    @GetMapping("/{name}/tags")
    public String getTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    // Viser tom formular til oprettelse af ny attraktion
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("allCities", touristService.getCities());
        model.addAttribute("allTags", touristService.getTags());
        return "add";
    }



    // Gemmer ny attraktion og sender brugeren tilbage til listen
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    // Viser formular forudfyldt med eksisterende data til redigering
    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("allCities", touristService.getCities());
        model.addAttribute("allTags", touristService.getTags());
        return "updateAttraction";
    }

    // Gemmer ændringer og sender brugeren tilbage til listen
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction.getName(), attraction);
        return "redirect:/attractions";
    }

    // Sletter attraktion og sender brugeren tilbage til listen
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }
}