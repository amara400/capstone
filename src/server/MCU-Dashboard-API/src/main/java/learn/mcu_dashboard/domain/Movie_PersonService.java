package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.data.Movie_PersonRepository;
import learn.mcu_dashboard.data.PersonRepository;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Movie_Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Movie_PersonService {

    private final Movie_PersonRepository movie_personRepository;
    private final PersonRepository personRepository;

    public Movie_PersonService(Movie_PersonRepository movie_personRepository, PersonRepository personRepository) {
        this.movie_personRepository = movie_personRepository;
        this.personRepository = personRepository;
    }

    public List<Movie_Person> findByIdMovie(int idMovie){return movie_personRepository.findByIdMovie(idMovie);}

    public Result<Movie_Person> add(Movie_Person movie_person) {

        Result<Movie_Person> result = validate(movie_person);
        if (!result.isSuccess()) {
            return result;
        }

//        if(personRepository.findPersonByName(movie_person.getPerson().getName()) == null) {
//            personRepository.add(movie_person.getPerson());
//        }

        movie_person = movie_personRepository.add(movie_person);
        result.setPayload(movie_person);
        return result;
    }

    public boolean deleteByKey(int idMovie, String role, int idPerson) {
        return movie_personRepository.deleteByKey(idMovie, role, idPerson);
    }

    // Support method
    private Result<Movie_Person> validate(Movie_Person movie_person) {
        Result<Movie_Person> result = new Result<>();
        if (movie_person == null) {
            result.addMessage("movie_person can not be null", ResultType.INVALID);
            return result;
        }

        // Needs positive idMovie
        if (( movie_person.getIdMovie() <= 0 )) {
            result.addMessage("idMovie must be  positive integer", ResultType.INVALID);
        }

        // Needs a role
        if (Validations.isNullOrBlank(movie_person.getRole())) {
            result.addMessage("Role is required", ResultType.INVALID);
        }

        //  Role must be either Director, Producer, or Cast
        if (movie_person.getRole() != "Director" && movie_person.getRole() != "Producer" && movie_person.getRole() != "Cast") {
            result.addMessage("Role must be either Director, Producer, or Cast", ResultType.INVALID);
        }


        // Needs positive idPerson
        if (( movie_person.getIdPerson() <= 0 )) {
            result.addMessage("idPerson must be  positive integer", ResultType.INVALID);
        }

        return result;
    }


}
