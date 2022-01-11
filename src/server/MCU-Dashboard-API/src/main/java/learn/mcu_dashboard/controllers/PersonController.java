package learn.mcu_dashboard.controllers;

import learn.mcu_dashboard.domain.MovieService;
import learn.mcu_dashboard.domain.PersonService;
import learn.mcu_dashboard.domain.Result;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {this.service = service;}

    @GetMapping("/{idPerson}")
    public Person findById(@PathVariable int idPerson) {return service.findById(idPerson);}

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Person person) {
        System.out.println("Post");
        Result<Person> result = service.add(person);
        System.out.println("Results");
        System.out.println(result.getMessages());
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity<Void> deleteById(@PathVariable int idPerson) {
        if (service.deleteById(idPerson)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
