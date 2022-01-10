package learn.mcu_dashboard.data.mappers;

import learn.mcu_dashboard.models.Movie;
import learn.mcu_dashboard.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setIdPerson(resultSet.getInt("idPerson"));
        person.setName(resultSet.getString("name"));

        return person;
    }
}
