package com.example.touristguideapi;

import com.example.touristguideapi.model.TouristAttraction;
import com.example.touristguideapi.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristGuideApiApplicationTests {

    @Autowired
    TouristRepository tr;

    @Test
    void findFirst() {
        List<TouristAttraction> all = tr.getAllAttractions();

        assertThat(all).isNotNull();
        assertThat(all.getFirst().getName()).isEqualTo("Tivoli");
        assertThat(all.getLast().getName()).isEqualTo("TuristInformation");

    }

    @Test
    void save() {
        tr.addAttraction( new TouristAttraction("lol-o-hoj","description", "city", List.of("fun","notfun"), "tivoli.png"));
        List<TouristAttraction> all = tr.getAllAttractions();
        assertThat(all).isNotNull();
        assertThat(all.getLast().getName()).isEqualTo("lol-o-hoj");
    }
}

