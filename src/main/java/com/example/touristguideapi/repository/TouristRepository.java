package com.example.touristguideapi.repository;

import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class TouristRepository {

    ArrayList<TouristAttraction> list = new ArrayList<>();

    // Hardkodede lister til select- og checkbox-udfyldning i formularerne
    private final List<String> cities = Arrays.asList(
            "Albertslund", "København", "Odense", "Kongens Lyngby", "Aarhus");

    private final List<String> tags = Arrays.asList(
            "Børnevenlig", "Gratis", "Kunst", "Museum", "Natur", "Underholdning");

    public TouristRepository() {
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
    }

    public List<TouristAttraction> getAllAttractions() {
        return Collections.unmodifiableList(list);
    }

    public void addAttraction(TouristAttraction ta) {
        list.add(ta);
    }

    public TouristAttraction getAttractionNumber(int number) {
        return list.get(number);
    }

    public void deleteAttractionNumber(int number) {
        list.remove(number);
    }

    public void updateAttraction(String name, TouristAttraction updated) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                list.set(i, updated);
                return;
            }
        }
    }

    public TouristAttraction searchAttractionByString(String search) {
        for (TouristAttraction t : list) {
            if (t.getName().toLowerCase().contains(search.toLowerCase())
                    || t.getDescription().toLowerCase().contains(search.toLowerCase())) {
                return t;
            }
        }
        return null;
    }

    public void deleteAttractionByString(String search) {
        list.removeIf(t -> t.getName().toLowerCase().contains(search.toLowerCase()));
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getTags() {
        return tags;
    }
}