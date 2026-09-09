package com.example.touristguideapi.controller;

import com.example.touristguideapi.repository.TouristRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AttractionListController {
    TouristRepository tr = new TouristRepository();

@GetMapping
public String getAttractions(Model model){
    model.addAttribute("attraction", tr.getAllAttractions());
    return "attractionList";
}

}
