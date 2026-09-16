package com.example.touristguideapi.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private String image;

    // Tom konstruktør - nødvendig for Thymeleaf form-binding (@ModelAttribute)
    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, String city, List<String> tags, String image) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.image = image;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Navn: " + name + ", Beskrivelse: " + description + ", By: " + city + ", Tags: " + tags + ", Billede: " + image;
    }
}