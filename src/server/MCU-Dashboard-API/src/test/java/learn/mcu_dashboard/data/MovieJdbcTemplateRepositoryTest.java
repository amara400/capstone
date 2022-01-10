package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieJdbcTemplateRepositoryTest {

    @Autowired
    MovieJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void should_findById() {
        Movie testHulk = new Movie(2, "The Incredible Hulk", LocalDate.of(2008, 6, 13),
                112, 6.6f, 61,
                new BigDecimal(150000000), new BigDecimal(134806913),
                new BigDecimal(264770996), new BigDecimal(55414050),
                0, 0, "The Hulk");
        Movie hulk = repository.findById(2);
        assertEquals("The Incredible Hulk", hulk.getTitle());
        assertEquals(testHulk, hulk);

        Movie actual = repository.findById(300);
        assertEquals(null, actual);
    }

    @Test
    void should_findAll() {
        List<Movie> movies = repository.findAll();

        assertNotNull(movies);
        assertTrue(movies.size() >= 3);

        Movie testHulk = new Movie(2, "The Incredible Hulk", LocalDate.of(2008, 6, 13),
                112, 6.6f, 61,
                new BigDecimal(150000000), new BigDecimal(134806913),
                new BigDecimal(264770996), new BigDecimal(55414050),
                0, 0, "The Hulk");
        assertTrue(movies.contains(testHulk) &&
                movies.stream().anyMatch(i -> i.getIdMovie() == 3));

    }

    @Test
    void shouldAdd() {
        Movie suicide = makeMovie();

        Movie actual = repository.add(suicide);
        suicide.setIdMovie(5);

        assertNotNull(actual);
        assertEquals(suicide, actual);
    }

    @Test
    void shouldUpdateExisting() {
        Movie ironMan = new Movie(1, "Iron Man 3", LocalDate.of(2013, 5, 3),
                130, 7.1f, 62,
                new BigDecimal(200000000), new BigDecimal(408992272),
                new BigDecimal(1214811252), new BigDecimal(174144585),
                1, 0, "Iron Man");

        assertTrue(repository.update(ironMan));
        assertEquals(ironMan, repository.findById(1));
    }

    @Test
    void shouldNotUpdateMissing(){
        Movie testHulk = new Movie(200, "The Incredible Hulk", LocalDate.of(2008, 6, 13),
                112, 6.6f, 61,
                new BigDecimal(150000000), new BigDecimal(134806913),
                new BigDecimal(264770996), new BigDecimal(55414050),
                0, 0, "The Hulk");
        assertFalse(repository.update(testHulk));
    }

    @Test
    void shouldDeleteByIdExisting() {
        assertTrue(repository.deleteById(4));
    }

    @Test
    void shouldNotDeleteByIdMissing() {
        assertFalse(repository.deleteById(400));
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