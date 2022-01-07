
package learn.mcu_dashboard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @MockBean
    MovieRepository repository;

    @Autowired
    MockMvc mvc;

    //    public Movie(int idMovie, String title, LocalDate usReleaseDate,
//                 int runtime, float imdbRating, int metascore,
//                 BigDecimal budget, BigDecimal domesticGross,
//                 BigDecimal totalGross, BigDecimal openingGross,
//                 int oscarNominations, int oscarsWon, String franchise)

    @Test
    void shouldGetAll() throws Exception {
        List<Movie> movies = List.of(
                new Movie(1, "Iron Man", LocalDate.of(2008, 05, 02), 126, 7.9f, 79, new BigDecimal(140000000),
                        new BigDecimal(318604126), new BigDecimal(585366247), new BigDecimal(98618668), 2, 0, "Iron Man"),
                new Movie(2, "The Incredible Hulk", LocalDate.of(2008, 06, 13), 112, 6.6f, 61, new BigDecimal(150000000),
                        new BigDecimal(134806913), new BigDecimal(264770996), new BigDecimal(55414050), 0, 0, "The Hulk"),
                new Movie(3, "Iron Man 2", LocalDate.of(2010, 05, 07), 124, 7f, 57, new BigDecimal(200000000),
                        new BigDecimal(312433331), new BigDecimal(623933331), new BigDecimal(128122480), 1, 0, "Iron Man"),
                new Movie(4, "Marvel's the Avengers", LocalDate.of(2012, 05, 04), 143, 8f, 69, new BigDecimal(220000000),
                        new BigDecimal(623357910), new BigDecimal(1518812988), new BigDecimal(207438708), 1, 0, "Avengers")
        );

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(movies);

        System.out.println(expectedJson);

        when(repository.findAll()).thenReturn(movies);

        mvc.perform(get("/api/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldGetById() throws Exception{
        Movie movie = new Movie(1, "Iron Man", LocalDate.of(2008, 05, 02), 126, 7.9f, 79, new BigDecimal(140000000),
                new BigDecimal(318604126), new BigDecimal(585366247), new BigDecimal(98618668), 2, 0, "Iron Man");

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(movie);

        System.out.println(expectedJson);

        when(repository.findById(1)).thenReturn(movie);

        mvc.perform(get("/api/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldAdd() throws Exception{
        Movie movieIn = new Movie(0, "Iron Man", LocalDate.of(2008, 05, 02), 126, 7.9f, 79, new BigDecimal(140000000),
                new BigDecimal(318604126), new BigDecimal(585366247), new BigDecimal(98618668), 2, 0, "Iron Man");
        Movie expected = new Movie(1, "Iron Man", LocalDate.of(2008, 05, 02), 126, 7.9f, 79, new BigDecimal(140000000),
                new BigDecimal(318604126), new BigDecimal(585366247), new BigDecimal(98618668), 2, 0, "Iron Man");

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(movieIn);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}
