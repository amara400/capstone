package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.Movie_PersonRepository;
import learn.mcu_dashboard.data.PersonRepository;
import learn.mcu_dashboard.models.Movie_Person;
import learn.mcu_dashboard.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class Movie_PersonServiceTest {

    @Autowired
    Movie_PersonService service;

    @MockBean
    Movie_PersonRepository repository;

    @Test
    void findByIdMovie() {
        Movie_Person uno = makeMovie_Person();
        Movie_Person dos = makeMovie_Person();
        dos.setIdPerson(2);
        Movie_Person tres = makeMovie_Person();
        dos.setIdPerson(3);

        ArrayList<Movie_Person> expected = new ArrayList<>();
        expected.add(uno);
        expected.add(dos);
        expected.add(tres);

        when(repository.findByIdMovie(1)).thenReturn(expected);
        List<Movie_Person> actual = service.findByIdMovie(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Movie_Person movie_personIn = makeMovie_Person();

        Movie_Person movie_personOut = makeMovie_Person();

        when(repository.add(movie_personIn)).thenReturn(movie_personOut);

        Result<Movie_Person> result = service.add(movie_personIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(movie_personOut, result.getPayload());
    }

    @Test
    void shouldNotAddMovie_PersonWithIncorrectRole() {
        //should not add with blank role
        Movie_Person movie_personIn = makeMovie_Person();
        movie_personIn.setRole("");

        Movie_Person movie_personOut = makeMovie_Person();
        movie_personOut.setRole("");

        when(repository.add(movie_personIn)).thenReturn(movie_personOut);

        Result<Movie_Person> result = service.add(movie_personIn);
        result = service.add(movie_personIn);
        assertEquals(ResultType.INVALID, result.getType());

        //should not add with non Director/Producer/Actor
        movie_personIn = makeMovie_Person();
        movie_personIn.setRole("Viewer");

        movie_personOut = makeMovie_Person();
        movie_personOut.setRole("Viewer");

        when(repository.add(movie_personIn)).thenReturn(movie_personOut);

        result = service.add(movie_personIn);
        result = service.add(movie_personIn);
        assertEquals(ResultType.INVALID, result.getType());
    }


    @Test
    void shouldDeleteByKey(){
        when(repository.deleteByKey(4,"Cast",16)).thenReturn(true);
        assertTrue(service.deleteByKey(4,"Cast",16));
    }

    @Test
    void shouldNotDeleteByIncorrectKey(){
        when(repository.deleteByKey(5,"Cast",99)).thenReturn(false);
        assertFalse(service.deleteByKey(5,"Cast",99));
    }

    private Movie_Person makeMovie_Person() {
        Movie_Person test = new Movie_Person();
        test.setIdMovie(4);
        test.setRole("Cast");
        test.setIdPerson(1);

        return test;
    }
}