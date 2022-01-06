package learn.mcu_dashboard.domain;

import learn.mcu_dashboard.data.MovieRepository;
import learn.mcu_dashboard.models.Movie;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {this.repository = repository;}

    public List<Movie> findAll(){return repository.findAll();}

    public Movie findById(int idMovie) {return repository.findById(idMovie);}

    public Result<Movie> add(Movie movie) {
        Result<Movie> result = validate(movie);
        if (!result.isSuccess()) {
            return result;
        }

        if (movie.getIdMovie() != 0) {
            result.addMessage("idMovie cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        movie = repository.add(movie);
        result.setPayload(movie);
        return result;
    }

    public Result<Movie> update(Movie movie) {
        Result<Movie> result = validate(movie);
        if (!result.isSuccess()) {
            return result;
        }

        if (movie.getIdMovie() <= 0) {
            result.addMessage("idMovie must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(movie)) {
            String msg = String.format("idMovie: %s, not found", movie.getIdMovie());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int idMovie) {return repository.deleteById(idMovie);}

    // Support method
    private Result<Movie> validate(Movie movie) {
        Result<Movie> result = new Result<>();
        if (movie == null) {
            result.addMessage("movie can not be null", ResultType.INVALID);
            return result;
        }
        // Needs a title
        if (Validations.isNullOrBlank(movie.getTitle())) {
            result.addMessage("Title is required", ResultType.INVALID);
        }
        // Needs a release date
        if (Validations.isNull(movie.getUsReleaseDate())) {
            result.addMessage("Release date is required", ResultType.INVALID);
        }
        // Needs a runtime
        if (( movie.getRuntime() <= 0 )) {
            result.addMessage("Runtime must be  positive integer", ResultType.INVALID);
        }

        //IMDB rating must be 0 (default) or between 1 and 10
        if(!(movie.getImdbRating()== 0.0f || (movie.getImdbRating() >= 1.0f && movie.getImdbRating() <= 10.0f))){
            result.addMessage("IMDB rating must be between 1.0 and 10.0", ResultType.INVALID);
        }

        if(movie.getMetascore() < 0 || movie.getMetascore() > 100){
            result.addMessage("Metascore must be between 1 and 100", ResultType.INVALID);
        }

        if(Validations.isNegative(movie.getBudget())){
            result.addMessage("Budget cannot be negative", ResultType.INVALID);
        }

        if(Validations.isNegative(movie.getDomesticGross())){
            result.addMessage("Domestic Gross cannot be negative", ResultType.INVALID);
        }

        if(Validations.isNegative(movie.getTotalGross())){
            result.addMessage("Total Gross cannot be negative", ResultType.INVALID);
        }

        if(Validations.isNegative(movie.getOpeningGross())){
            result.addMessage("Opening Gross cannot be negative", ResultType.INVALID);
        }

        if(movie.getOscarNominations() < 0){
            result.addMessage("Oscar Nominations cannot be negative", ResultType.INVALID);
        }

        if (movie.getOscarsWon() < 0){
            result.addMessage("Oscars Won cannot be negative", ResultType.INVALID);
        }
        return result;
    }
}
