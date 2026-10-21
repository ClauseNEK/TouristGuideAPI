package com.example.touristguideapi.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Mapper én række fra tourist_attraction (joinet med city) til et TouristAttraction-objekt.
// Tags hentes separat i repository, da de ligger i koblingstabellen attraction_tag.
public class TouristAttractionRowMapper implements RowMapper<TouristAttraction> {

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TouristAttraction(
                rs.getInt("attraction_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("city_name"),
                new ArrayList<>(),
                rs.getString("image")
        );
    }
}
