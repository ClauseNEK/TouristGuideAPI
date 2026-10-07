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
/*

//not sure about final stuff, troede måske det ku spare i nogle funktioner....ikke sikker på hvor smart det er...
    private final List<TouristAttraction> ta_list = new ArrayList<>();
    private static final String sql_get_all = "SELECT * FROM touristattraction";
    private static final String sql_get_all_tags = "SELECT * FROM tags";
    private static final String sql_get_all_city = "SELECT * FROM City";
    private static final String sql_get_ta_by_id = "SELECT * FROM touristattraction WHERE id = ?";
    private static final String sql_get_ta_by_name = "SELECT * FROM touristattraction WHERE name = ?";
    private static final String sql_delete_ta_by_id = "DELETE * FROM touristattraction WHERE id = ?"
    private static final String sql_update_ta_by_id = "UPDATE * FROM touristattraction WHERE id = ?";

    private static final String id = "touristattraction_id";
    private static final String name = "touristattraction_name";
    private static final String description = "touristattraction_desciption";
    private static final String city = "touristattraction_city";
    private static final String tags = "touristattraction_tags";

    JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
    }

    //tilføj via insert into
    public void addAttraction(String name, String description, String city, Strings... tags){

    String sql_add = "INSERT INTO touristattraction(name, description, city, tags) VALUES (?,?,?,?)";
    jdbcTemplate.update(sql_add, name, description, city, tags);
}

//lave update, måske det skal ske for alle dele, så man kan nøjes med at opdatere én ting navn/des/whatever eller løsning med tomme updates, betyder det ikke updateres?
//Der findes nok en smartere måde end samme metode med forskellige argumenter
    public void updateAttraction(String name, String description, String city, String... tags){
    String sql_update = "UPDATE touristattraction(name, description, city, tags) VALUES (?,?,?,?)";

    jdbcTemplate.update(sql_add, name, description, city, tags);
}

//delete where id = ?
    public void delteAttraction(int attration_id){
    String sql_delete = "DELETE FROM touristattraction WHERE touristattraction_id = ?";

    jdbcTemplate.update(sql_delete, attration_id);
}


  public List<TouristAttraction> findAll() {
        String sql = "SELECT id, name FROM touristattraction";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new TouristAttraction(rs.getInt("id"), rs.getString("name"),rs.getString("description"),rs.getString("city"),List.of(rs.getString("tags")))
        );

        //Kan også gøre sådan her:
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM touristattraction");

        while (rowSet.next()) {
            int id = rowSet.getInt("id");
            String name = rowSet.getString("name");
            String description = rowSet.getString("description");
            String city = rowSet.getString("city");
            String tags = rowSet.getString("tags");
            ta_list.add(new TouristAttraction(id, name, description, city, List.of(tags)));
        }

        //Kan også gøres sådan her, hvis rowmapper klassen er sat op ordenligt...Vi kommer nok til at mangle id i TouristAttraction
        List<touristattraction> ta_list = jdbcTemplate.query("SELECT * FROM touristattraction", new TouristAttractionRowMapper());
*/


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