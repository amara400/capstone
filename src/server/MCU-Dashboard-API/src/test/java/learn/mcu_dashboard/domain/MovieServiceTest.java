
package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest {

    @Autowired
    MovieService service;

    @MockBean
    MovieRepository repository;

    @Test
    void should_findById() {
        Movie expected = makeMovie();
        when(repository.findById(1)).thenReturn(expected);
        Movie actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd(){
        Movie movieIn = makeMovie();
        movieIn.setIdMovie(0);
        Movie movieOut = makeMovie();

        when(repository.add(movieIn)).thenReturn(movieOut);

        Result<Movie> result = service.add(movieIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(movieOut, result.getPayload());

        //minimal data works
        movieIn = new Movie();
        movieIn.setTitle("Suicide Squad");
        movieIn.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movieIn.setRuntime(93);

        movieOut = new Movie();
        movieOut.setTitle("Suicide Squad");
        movieOut.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movieOut.setRuntime(93);

        when(repository.add(movieIn)).thenReturn(movieOut);

        result = service.add(movieIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(movieOut, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        //idMovie !=0
        Movie movie = makeMovie();
        Result<Movie> result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //null title
        movie.setIdMovie(0);
        movie.setTitle(null);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //blank title
        movie.setTitle("");
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //null release date
        movie.setTitle("Suicide Squad");
        movie.setUsReleaseDate(null);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative duration
        movie.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movie.setRuntime(-100);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //out of bounds IMDB Rating
        movie.setRuntime(93);
        movie.setImdbRating(0.3f);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        movie.setImdbRating(20.3f);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //out of bounds metascore

        movie.setImdbRating(5.1f);
        movie.setMetascore(-10);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        movie.setMetascore(150);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative budget
        movie.setMetascore(26);
        movie.setBudget(new BigDecimal(-10000));
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative domestic gross
        movie.setBudget(new BigDecimal(1000000000));
        movie.setDomesticGross(new BigDecimal(-10000));
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative total gross
        movie.setDomesticGross(new BigDecimal(11000000));
        movie.setTotalGross(new BigDecimal( -100000));
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative opening gross
        movie.setTotalGross(new BigDecimal(16000000));
        movie.setOpeningGross(new BigDecimal(-10000));
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative oscar nominations
        movie.setOpeningGross(new BigDecimal(3500000));
        movie.setOscarNominations(-1);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        //negative oscars won
        movie.setOscarNominations(0);
        movie.setOscarsWon(-1);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate(){
        Movie movieIn = makeMovie();

        when(repository.update(movieIn)).thenReturn(true);

        Result<Movie> result = service.update(movieIn);

        System.out.println(result.getMessages());

        assertEquals(ResultType.SUCCESS, result.getType());
        assertNull(result.getPayload());

        //Minimal data
        movieIn = new Movie();
        movieIn.setIdMovie(1);
        movieIn.setTitle("Suicide Squad");
        movieIn.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movieIn.setRuntime(93);

        when(repository.update(movieIn)).thenReturn(true);

        result = service.update(movieIn);

        System.out.println(result.getMessages());

        assertEquals(ResultType.SUCCESS, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWhenInvalid(){
        //idMovie not found
        Movie movie = makeMovie();
        when(repository.update(movie)).thenReturn(false);
        Result<Movie> result = service.update(movie);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());

        //null movie
        result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //null title
        movie.setIdMovie(0);
        movie.setTitle(null);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

<<<<<<< HEAD
=======
    @Test
    void shouldUpdate(){
        Movie movieIn = makeMovie();

        when(repository.update(movieIn)).thenReturn(true);

        Result<Movie> result = service.update(movieIn);

        System.out.println(result.getMessages());

        assertEquals(ResultType.SUCCESS, result.getType());
        assertNull(result.getPayload());

        //Minimal data
        movieIn = new Movie();
        movieIn.setIdMovie(1);
        movieIn.setTitle("Suicide Squad");
        movieIn.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movieIn.setRuntime(93);

        when(repository.update(movieIn)).thenReturn(true);

        result = service.update(movieIn);

        System.out.println(result.getMessages());

        assertEquals(ResultType.SUCCESS, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWhenInvalid(){
        //idMovie not found
        Movie movie = makeMovie();
        when(repository.update(movie)).thenReturn(false);
        Result<Movie> result = service.update(movie);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());

        //null movie
        result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //null title
        movie.setIdMovie(0);
        movie.setTitle(null);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

>>>>>>> 8cace8eefa78bd59b353c2a82044fc8c235d2589
        //blank title
        movie.setTitle("");
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
<<<<<<< HEAD
=======

        //null release date
        movie.setTitle("Suicide Squad");
        movie.setUsReleaseDate(null);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative duration
        movie.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movie.setRuntime(-100);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //out of bounds IMDB Rating
        movie.setRuntime(93);
        movie.setImdbRating(0.3f);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        movie.setImdbRating(20.3f);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //out of bounds metascore

        movie.setImdbRating(5.1f);
        movie.setMetascore(-10);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        movie.setMetascore(150);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative budget
        movie.setMetascore(26);
        movie.setBudget(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative domestic gross
        movie.setBudget(new BigDecimal(1000000000));
        movie.setDomesticGross(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative total gross
        movie.setDomesticGross(new BigDecimal(11000000));
        movie.setTotalGross(new BigDecimal( -100000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative opening gross
        movie.setTotalGross(new BigDecimal(16000000));
        movie.setOpeningGross(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative oscar nominations
        movie.setOpeningGross(new BigDecimal(3500000));
        movie.setOscarNominations(-1);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative oscars won
        movie.setOscarNominations(0);
        movie.setOscarsWon(-1);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldDelete(){
        when(repository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing(){
        when(repository.deleteById(20)).thenReturn(false);
        assertFalse(service.deleteById(20));
    }

>>>>>>> 8cace8eefa78bd59b353c2a82044fc8c235d2589

        //null release date
        movie.setTitle("Suicide Squad");
        movie.setUsReleaseDate(null);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative duration
        movie.setUsReleaseDate(LocalDate.of(2016, 4, 20));
        movie.setRuntime(-100);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //out of bounds IMDB Rating
        movie.setRuntime(93);
        movie.setImdbRating(0.3f);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        movie.setImdbRating(20.3f);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //out of bounds metascore

        movie.setImdbRating(5.1f);
        movie.setMetascore(-10);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        movie.setMetascore(150);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative budget
        movie.setMetascore(26);
        movie.setBudget(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative domestic gross
        movie.setBudget(new BigDecimal(1000000000));
        movie.setDomesticGross(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative total gross
        movie.setDomesticGross(new BigDecimal(11000000));
        movie.setTotalGross(new BigDecimal( -100000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative opening gross
        movie.setTotalGross(new BigDecimal(16000000));
        movie.setOpeningGross(new BigDecimal(-10000));
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative oscar nominations
        movie.setOpeningGross(new BigDecimal(3500000));
        movie.setOscarNominations(-1);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());

        //negative oscars won
        movie.setOscarNominations(0);
        movie.setOscarsWon(-1);
        result = service.update(movie);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldDelete(){
        when(repository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing(){
        when(repository.deleteById(20)).thenReturn(false);
        assertFalse(service.deleteById(20));
    }

    private Movie makeMovie() {
        Movie movie = new Movie();
        movie.setIdMovie(1);
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
