package learn.mcu_dashboard.data;

import learn.mcu_dashboard.data.mappers.MovieMapper;
import learn.mcu_dashboard.data.mappers.Movie_PersonMapper;
import learn.mcu_dashboard.models.Movie_Person;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Movie_PersonJdbcTemplateRepository implements Movie_PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public Movie_PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie_Person> findByIdMovie(int idMovie) {
        final String sql = "select mp.idMovie, mp.`role`, p.`name` " +
                "from Movie_Person mp " +
                "inner join Person p on mp.idPerson = p.idPerson" +
                "where idMovie = ?";

//                final String sql = "select idMovie, `role`, idPerson " +
//                "from Movie_Person " +
//                "where idMovie = ?";

        return jdbcTemplate.query(sql, new Movie_PersonMapper());
    }

    @Override
    public boolean add(Movie_Person movie_person) {

        final String sql = "insert into movie_person (idMovie, `role`, idPerson) " +
                "values (?,?,?);";

        return jdbcTemplate.update(sql,
                movie_person.getIdMovie(),
                movie_person.getRole(),
                movie_person.getIdPerson()) > 0;
    }

    // No update for now, you can either add or delete

//    @Override
//    public boolean update(Movie_Person movie_person) {
//
//        final String sql = "update Movie_Person set "
//                + "idMovie = ?, "
//                + "`role` = ?, "
//                + "idPerson = ?, "
//                + "where idMovie = ? and agent_id = ?;";
//
//        return jdbcTemplate.update(sql,
//                agencyAgent.getIdentifier(),
//                agencyAgent.getSecurityClearance().getSecurityClearanceId(),
//                agencyAgent.getActivationDate(),
//                agencyAgent.isActive(),
//                agencyAgent.getAgencyId(),
//                agencyAgent.getAgent().getAgentId()) > 0;
//
//    }

    @Override
    public boolean deleteByKey(int idMovie, String role, int idPerson) {

        final String sql = "delete from Movie_Person "
                + "where idMovie = ? and `role` = ? and idPerson = ?;";

        return jdbcTemplate.update(sql, idMovie, role, idPerson) > 0;
    }

}
