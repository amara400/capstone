package learn.mcu_dashboard.controllers;

import learn.mcu_dashboard.domain.MovieService;
import learn.mcu_dashboard.domain.Result;
import learn.mcu_dashboard.models.Movie;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {this.service = service;}

    @GetMapping
    public List<Movie> findAll() {return service.findAll();}

    @GetMapping("/{idMovie}")
    public Movie findById(@PathVariable int idMovie) {return service.findById(idMovie);}

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Movie movie) {
        System.out.println("Post");
        Result<Movie> result = service.add(movie);
        System.out.println("Resutls");
        System.out.println(result.getMessages());
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{idMovie}")
    public ResponseEntity<Object> update(@PathVariable int idMovie, @RequestBody Movie movie) {

        if (idMovie != movie.getIdMovie()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Movie> result = service.update(movie);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{idMovie}")
    public ResponseEntity<Void> deleteById(@PathVariable int idMovie) {
        if (service.deleteById(idMovie)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
