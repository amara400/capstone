package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie_Person;
import learn.mcu_dashboard.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Movie_PersonRepositoryTest {

    @Autowired
    Movie_PersonJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void findByIdMovie() {
        List<Movie_Person> actual = repository.findByIdMovie(1);
        assertEquals(6, actual.size());
    }

    @Test
    void shouldFindNothingForNonexistentIdMovie() {
        List<Movie_Person> actual = repository.findByIdMovie(49);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldAdd() {
        Movie_Person movie_person = makeMovie_Person();
        Movie_Person actual = repository.add(movie_person);

        assertNotNull(actual);
        assertEquals("Cast", actual.getRole());
    }

    @Test
    void shouldDeleteByKey() {
        Movie_Person movie_person = makeMovie_Person();
        Movie_Person actual = repository.add(movie_person);

        assertTrue(repository.deleteByKey(4,"Cast",1));
    }

    @Test
    void shouldFindReferencedPerson() {
        assertTrue(repository.findPersonByIdPerson(1));

    }

    @Test
    void shouldNotDeleteByNonexistentKey() {
        assertFalse(repository.deleteByKey(5,"Cast",41));
    }

    private Movie_Person makeMovie_Person() {
        Movie_Person test = new Movie_Person();
        test.setIdMovie(4);
        test.setRole("Cast");
        test.setIdPerson(1);

        return test;
    }
}