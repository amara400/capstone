package learn.mcu_dashboard.data.mappers;

import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Movie_Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie_PersonMapper implements RowMapper<Movie_Person> {

    @Override
    public Movie_Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie_Person movie_person = new Movie_Person();
        movie_person.setIdMovie(resultSet.getInt("idMovie"));
        movie_person.setRole(resultSet.getString("role"));
        movie_person.setIdPerson(resultSet.getInt("idPerson"));

        PersonMapper personMapper = new PersonMapper();
        movie_person.setPerson(personMapper.mapRow(resultSet, i));

        return movie_person;
    }

}
