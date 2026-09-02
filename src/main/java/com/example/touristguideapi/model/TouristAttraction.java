package com.example.touristguideapi.model;

public class TouristAttraction {
    private String name;
    private String description;

    //Constructor (maybe not need)
    public TouristAttraction(String name, String description){
        this.name = name;
        this.description = description;
    }

    // Getters \\
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    // Setters if needed \\
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String Description){
        this.description = Description;
    }


    //toString override
    @Override
    public String toString() {
        return "Navn: " + name + ", Beskrivelse: " + description;
    }
}
