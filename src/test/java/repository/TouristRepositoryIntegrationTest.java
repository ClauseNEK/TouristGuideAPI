package com.example.touristguideapi.repository;

import com.example.touristguideapi.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Integrationstest: Repository + JdbcTemplate + rigtige SQL-scripts mod H2 (MySQL-mode).
// @Transactional ruller hver test tilbage, så testene ikke påvirker hinanden.
@SpringBootTest
@Transactional
class TouristRepositoryIntegrationTest {

    @Autowired
    private TouristRepository repository;

    @Test
    void getAllAttractions_returnsSeedData() {
        List<TouristAttraction> all = repository.getAllAttractions();
        assertEquals(5, all.size());
        assertEquals("Tivoli", all.get(0).getName());
        assertEquals("København", all.get(0).getCity());
        assertEquals(List.of("Børnevenlig", "Underholdning"), all.get(0).getTags());
    }

    @Test
    void searchAttractionByString_isCaseInsensitive() {
        TouristAttraction vega = repository.searchAttractionByString("vega");
        assertNotNull(vega);
        assertEquals("Vega", vega.getName());
        assertEquals(List.of("Underholdning"), vega.getTags());
    }

    @Test
    void searchAttractionByString_unknownReturnsNull() {
        assertNull(repository.searchAttractionByString("findes ikke"));
    }

    @Test
    void addAttraction_savesAttractionAndTags() {
        repository.addAttraction(new TouristAttraction("Den Blå Planet", "Akvarium", "København",
                List.of("Børnevenlig", "Natur"), "blaaplanet.png"));

        TouristAttraction saved = repository.searchAttractionByString("Den Blå Planet");
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals("København", saved.getCity());
        assertEquals(List.of("Børnevenlig", "Natur"), saved.getTags());
        assertEquals(6, repository.getAllAttractions().size());
    }

    @Test
    void updateAttraction_changesFieldsAndReplacesTags() {
        repository.updateAttraction("Bakken", new TouristAttraction("Bakken", "Verdens ældste forlystelsespark",
                "Albertslund", List.of("Gratis"), "bakken_logo_2026.png"));

        TouristAttraction bakken = repository.searchAttractionByString("Bakken");
        assertEquals("Verdens ældste forlystelsespark", bakken.getDescription());
        assertEquals("Albertslund", bakken.getCity());
        assertEquals(List.of("Gratis"), bakken.getTags());
    }

    @Test
    void deleteAttractionByString_removesAttraction() {
        repository.deleteAttractionByString("Royal Arena");
        assertNull(repository.searchAttractionByString("Royal Arena"));
        assertEquals(4, repository.getAllAttractions().size());
    }

    @Test
    void getCitiesAndTags_comeFromDatabase() {
        assertEquals(5, repository.getCities().size());
        assertTrue(repository.getTags().contains("Underholdning"));
    }
}
