package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    final static int NEXT_ID = 17;

    @Autowired
    PersonJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindById() {
        // 4	Robert Downey Jr.

        Person actual = repository.findById(4);
        assertEquals("Robert Downey Jr.", actual.getName());
    }

    @Test
    void shouldNotFindByNonexistentId() {
        Person actual = repository.findById(123);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Person andrew = makePerson();
        Person actual = repository.add(andrew);

        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getIdPerson());
    }

    // Cannot delete a child with parents
    // Only works because Andrew is not linked to a Movie_Person
    @Test
    void shouldDeleteById() {
        Person andrew = makePerson();
        Person actual = repository.add(andrew);

        assertTrue(repository.deleteById(NEXT_ID));
    }

    @Test
    void shouldNotDeleteByIdMissing() {
        assertFalse(repository.deleteById(400));
    }

    private Person makePerson() {
        Person person = new Person();
        //person.setIdPerson(NEXT_ID);
        person.setName("Andrew Garfield");
        return person;
    }
}