package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie_Person;

import java.util.List;

public interface Movie_PersonRepository {
    List<Movie_Person> findByIdMovie(int idMovie);

    boolean add(Movie_Person movie_person);

    boolean deleteByKey(int idMovie, String role, int idPerson);
}
