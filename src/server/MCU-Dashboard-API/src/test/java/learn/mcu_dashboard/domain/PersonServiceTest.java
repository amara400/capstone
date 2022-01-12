package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.data.PersonRepository;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService service;

    @MockBean
    PersonRepository repository;

    @Test
    void findById() {
        Person expected = makePerson();
        when(repository.findById(1)).thenReturn(expected);
        Person actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Person personIn = makePerson();
        personIn.setIdPerson(0);
        Person personOut = makePerson();

        when(repository.add(personIn)).thenReturn(personOut);

        Result<Person> result = service.add(personIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(personOut, result.getPayload());
    }

    @Test
    void shouldNotAddPersonWithoutName() {
        //should not add blank name
        Person personIn = makePerson();
        personIn.setName("");

        Person personOut = makePerson();
        personOut.setName("");

        when(repository.add(personIn)).thenReturn(personOut);

        Result<Person> result = service.add(personIn);
        result = service.add(personIn);

        assertEquals(ResultType.INVALID, result.getType());
    }


    @Test
    void shouldDelete(){
        when(repository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing(){
        when(repository.deleteById(40)).thenReturn(false);
        assertFalse(service.deleteById(40));
    }

    @Test
    void shouldNotDeleteReferencedPerson(){
        when(repository.deleteById(40)).thenReturn(false);
        assertFalse(service.deleteById(40));
    }

    private Person makePerson() {
        Person person = new Person();
        //person.setIdPerson(NEXT_ID);
        person.setName("Andrew Garfield");
        return person;
    }
}