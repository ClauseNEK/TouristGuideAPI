package com.example.touristguideapi.repository;

import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
Opret klassen TouristRepository i repository package med annoteringen
@Repository.
Tilføj en ArrayList til opbevaring af data (om ikke så længe skal I arbejde med en
rigtig database). Opret et par TouristAttraction objekter, som tilføjes til denne
ArrayList.
Klassen skal desuden indeholde CRUD metoder, der arbejder på ovenstående
ArrayList. Vent evt. med den endelige metodesignatur for CRUD metoderne til I
har set beskrivelsen af Controller klassens endpoints nedenfor.
 */
@Repository
public class TouristRepository {
    ArrayList<TouristAttraction> list = new ArrayList<>();

    public TouristRepository(){
        list.add(new TouristAttraction("Tivoli","Sjov for hele familien"));
        list.add(new TouristAttraction("Bakken","Sjov for hele familien"));
        list.add(new TouristAttraction("Vega","Musik året rundt"));
        list.add(new TouristAttraction("Royal Arena","Koncerter mm."));
    }

    public List<TouristAttraction> getAllAttractions(){
        return list;
    }

    public void addAttraction(TouristAttraction TA){
        list.add(TA);
    }

    public TouristAttraction getAttractionNumber(int number){
        return list.get(number);
    }

    public void deleteAttractionNumber(int number){
        list.remove(number);
    }

    public void updateAttraction(int number, TouristAttraction TA){
        list.set(number,TA);
    }


}
