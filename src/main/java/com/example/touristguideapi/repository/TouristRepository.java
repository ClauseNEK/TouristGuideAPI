package com.example.touristguideapi.repository;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.model.TouristAttractionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TouristRepository {

    private static final String SELECT_ATTRACTION =
            "SELECT a.attraction_id, a.name, a.description, a.image, c.city_name " +
            "FROM tourist_attraction a JOIN city c ON a.city_id = c.city_id ";

    private final JdbcTemplate jdbcTemplate;
    private final TouristAttractionRowMapper rowMapper = new TouristAttractionRowMapper();

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // READ - alle attraktioner inkl. tags
    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> attractions =
                jdbcTemplate.query(SELECT_ATTRACTION + "ORDER BY a.attraction_id", rowMapper);
        attractions.forEach(a -> a.setTags(getTagsForAttraction(a.getId())));
        return attractions;
    }

    // READ - én attraktion ud fra navn (bruges af /attractions/{name}/...)
    public TouristAttraction searchAttractionByString(String name) {
        List<TouristAttraction> result = jdbcTemplate.query(
                SELECT_ATTRACTION + "WHERE LOWER(a.name) = LOWER(?)", rowMapper, name);
        if (result.isEmpty()) {
            return null;
        }
        TouristAttraction attraction = result.get(0);
        attraction.setTags(getTagsForAttraction(attraction.getId()));
        return attraction;
    }

    // CREATE - indsætter attraktion og dens tags
    public void addAttraction(TouristAttraction ta) {
        String sql = "INSERT INTO tourist_attraction (name, description, image, city_id) " +
                     "VALUES (?, ?, ?, (SELECT city_id FROM city WHERE city_name = ?))";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ta.getName());
            ps.setString(2, ta.getDescription());
            ps.setString(3, ta.getImage());
            ps.setString(4, ta.getCity());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        ta.setId(newId);
        insertTags(newId, ta.getTags());
    }

    // UPDATE - opdaterer attraktion (fundet via navn) og erstatter dens tags
    public void updateAttraction(String name, TouristAttraction updated) {
        TouristAttraction existing = searchAttractionByString(name);
        if (existing == null) {
            return;
        }
        String sql = "UPDATE tourist_attraction SET description = ?, image = ?, " +
                     "city_id = (SELECT city_id FROM city WHERE city_name = ?) " +
                     "WHERE attraction_id = ?";
        jdbcTemplate.update(sql, updated.getDescription(), updated.getImage(),
                updated.getCity(), existing.getId());

        jdbcTemplate.update("DELETE FROM attraction_tag WHERE attraction_id = ?", existing.getId());
        insertTags(existing.getId(), updated.getTags());
    }

    // DELETE - tags forsvinder automatisk pga. ON DELETE CASCADE
    public void deleteAttractionByString(String name) {
        jdbcTemplate.update("DELETE FROM tourist_attraction WHERE LOWER(name) = LOWER(?)", name);
    }

    // Til select-boksen i formularerne
    public List<String> getCities() {
        return jdbcTemplate.queryForList("SELECT city_name FROM city ORDER BY city_name", String.class);
    }

    // Til checkboksene i formularerne
    public List<String> getTags() {
        return jdbcTemplate.queryForList("SELECT tag_name FROM tag ORDER BY tag_name", String.class);
    }

    private List<String> getTagsForAttraction(int attractionId) {
        String sql = "SELECT t.tag_name FROM tag t " +
                     "JOIN attraction_tag atag ON t.tag_id = atag.tag_id " +
                     "WHERE atag.attraction_id = ? ORDER BY t.tag_name";
        return jdbcTemplate.queryForList(sql, String.class, attractionId);
    }

    private void insertTags(int attractionId, List<String> tags) {
        if (tags == null) {
            return;
        }
        String sql = "INSERT INTO attraction_tag (attraction_id, tag_id) " +
                     "VALUES (?, (SELECT tag_id FROM tag WHERE tag_name = ?))";
        for (String tag : tags) {
            jdbcTemplate.update(sql, attractionId, tag);
        }
    }
}
