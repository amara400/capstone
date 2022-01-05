package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void shouldNotAddWhenInvalid() {
        Movie movie = makeMovie();
        Result<Movie> result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());

        movie.setIdMovie(0);
        movie.setTitle(null);
        result = service.add(movie);
        assertEquals(ResultType.INVALID, result.getType());
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
