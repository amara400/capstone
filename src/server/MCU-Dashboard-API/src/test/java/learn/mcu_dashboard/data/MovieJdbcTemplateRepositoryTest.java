package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MovieJdbcTemplateRepositoryTest {

    @Autowired
    MovieJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void should_findById() {
        Movie hulk = repository.findById(2);
        assertEquals("The Incredible Hulk", hulk.getTitle());
    }

    @Test
    void should_findAll() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    private Movie makeMovie() {
        Movie movie = new Movie();
        movie.setTitle("Suicide Squad");
        movie.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movie.setRuntime(93);
        movie.setImdbRating(5.1f);
        movie.setMetascore(26);
        movie.setBudget(new BigDecimal(1000000000));
        movie.setDomesticGross(new BigDecimal(11000000));
        movie.setTotalGross(new BigDecimal(16000000));
        movie.setOpeningGross(new BigDecimal(3500000));
        movie.setOscarNominations(0);
        movie.setOscarsWon(0);
        movie.setFranchise("Suicide Squad");
        return movie;
    }
}