package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.data.PersonRepository;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {this.repository = repository;}

    public Person findById(int idPerson) {return repository.findById(idPerson);}

    public Result<Person> add(Person person) {
        Result<Person> result = validate(person);
        if (!result.isSuccess()) {
            return result;
        }

        if (person.getIdPerson() != 0) {
            result.addMessage("idPerson cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        person = repository.add(person);
        result.setPayload(person);
        return result;
    }

    public boolean deleteById(int idMovie) {return repository.deleteById(idMovie);}

    // Support method
    private Result<Person> validate(Person person) {
        Result<Person> result = new Result<>();

        if (person == null) {
            result.addMessage("person can not be null", ResultType.INVALID);
            return result;
        }

        // Needs name
        if (Validations.isNullOrBlank(person.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        return result;
    }

}
