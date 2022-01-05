package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Movie;

import java.util.List;

public interface MovieRepository {

    Movie findById(int idMovie);

    List<Movie> findAll();

    Movie add(Movie movie);

    boolean update(Movie movie);

    boolean deleteById(int idMovie);

}
