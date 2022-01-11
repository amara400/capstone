package learn.mcu_dashboard.controllers;

import learn.mcu_dashboard.domain.MovieService;
import learn.mcu_dashboard.domain.Movie_PersonService;
import learn.mcu_dashboard.domain.Result;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Movie_Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/movie_person")
public class Movie_PersonController {

    private final Movie_PersonService service;

    public Movie_PersonController(Movie_PersonService service) {this.service = service;}

    @GetMapping("/{idMovie}")
    public List<Movie_Person> findByIdMovie(@PathVariable int idMovie) {return service.findByIdMovie(idMovie);}

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Movie_Person movie_person) {
        System.out.println("Post");
        Result<Movie_Person> result = service.add(movie_person);
        System.out.println("Results");
        System.out.println(result.getMessages());
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{idMovie}/{role}/{idPerson}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int idMovie, String role, int idPerson) {
        if (service.deleteByKey(idMovie, role, idPerson)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
