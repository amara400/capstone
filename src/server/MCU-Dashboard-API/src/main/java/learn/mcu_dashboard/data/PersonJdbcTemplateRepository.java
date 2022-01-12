package learn.mcu_dashboard.data;

import learn.mcu_dashboard.data.mappers.Movie_PersonMapper;
import learn.mcu_dashboard.data.mappers.PersonMapper;
import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PersonJdbcTemplateRepository implements PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person findById(int idPerson) {

                final String sql = "select idPerson, name " +
                "from Person " +
                "where idPerson = ?;";

        return jdbcTemplate.query(sql, new PersonMapper(), idPerson).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Person findPersonByName(String name) {

        final String sql = "select idPerson, `name` " +
                "from Person " +
                "where `name` = ?;";

        return jdbcTemplate.query(sql, new PersonMapper(), name).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Person add(Person person) {

        final String sql = "insert into Person (`name`) " +
                "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        person.setIdPerson(keyHolder.getKey().intValue());
        return person;
    }

    @Override
    @Transactional
    public boolean deleteById(int idPerson) {
        return jdbcTemplate.update("delete from Person where idPerson = ?;", idPerson) > 0;
    }
}
