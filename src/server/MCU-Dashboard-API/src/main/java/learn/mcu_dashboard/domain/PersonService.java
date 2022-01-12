package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.data.Movie_PersonRepository;
import learn.mcu_dashboard.data.PersonRepository;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final Movie_PersonRepository movie_personRepository;

    public PersonService(PersonRepository personRepository, Movie_PersonRepository movie_personRepository) {
        this.personRepository = personRepository;
        this.movie_personRepository = movie_personRepository;
    }

    public Person findById(int idPerson) {return personRepository.findById(idPerson);}

    public Result<Person> add(Person person) {
        Result<Person> result = validate(person);
        if (!result.isSuccess()) {
            return result;
        }

        if (person.getIdPerson() != 0) {
            result.addMessage("idPerson cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        person = personRepository.add(person);
        result.setPayload(person);
        return result;
    }

    public boolean deleteById(int idPerson) {
        //Result<Person> result = new Result<>();

        if (movie_personRepository.findPersonByIdPerson(idPerson)) {
            //result.addMessage("Cannot delete a person referenced by Movie_Person", ResultType.INVALID);
            return false;
        }

        return personRepository.deleteById(idPerson);
    }

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
