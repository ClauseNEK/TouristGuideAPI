package com.example.touristguideapi.service;


import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    @Autowired
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttractions getAttractionsByName(String name) {
        return touristRepository.getAttractionsByName(name);
    }

    public boolean updateAttraction(String name, TouristAttraction updated) {
        return touristRepository.updateAttraction(name, updated);
    }

    public boolean deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }

}
