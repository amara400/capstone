package learn.mcu_dashboard.data;

import learn.mcu_dashboard.data.mappers.MovieMapper;
import learn.mcu_dashboard.data.mappers.Movie_PersonMapper;
import learn.mcu_dashboard.models.Movie_Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Movie_PersonJdbcTemplateRepository implements Movie_PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public Movie_PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie_Person> findByIdMovie(int idMovie) {
        final String sql = "select mp.idMovie, mp.`role`, p.idPerson, p.`name` " +
                "from Movie_Person mp " +
                "inner join Person p on mp.idPerson = p.idPerson " +
                "where idMovie = ?;";

        return jdbcTemplate.query(sql, new Movie_PersonMapper(), idMovie);
    }

    @Override
    public boolean findPersonByIdPerson(int idPerson) {
        final String sql = "select mp.idMovie, mp.`role`, p.idPerson, p.`name` " +
                "from Movie_Person mp " +
                "inner join Person p on mp.idPerson = p.idPerson " +
                "where mp.idPerson = ?;";

        List<Movie_Person> people = jdbcTemplate.query(sql, new Movie_PersonMapper(), idPerson);

        return (people.size() > 0);
    }

    @Override
    public Movie_Person add(Movie_Person movie_person) {

        final String sql = "insert into Movie_Person (idMovie, `role`, idPerson) " +
                "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, movie_person.getIdMovie());
            ps.setString(2, movie_person.getRole());
            ps.setInt(3, movie_person.getIdPerson());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        return movie_person;
    }

    // No update for now, you can either add or delete


    @Override
    public boolean deleteByKey(int idMovie, String role, int idPerson) {

        final String sql = "delete from Movie_Person "
                + "where idMovie = ? and `role` = ? and idPerson = ?;";

        return jdbcTemplate.update(sql, idMovie, role, idPerson) > 0;
    }

}
