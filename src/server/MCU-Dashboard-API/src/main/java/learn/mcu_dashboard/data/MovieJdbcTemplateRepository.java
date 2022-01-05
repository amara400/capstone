package learn.mcu_dashboard.data;

import learn.mcu_dashboard.data.mappers.MovieMapper;
import learn.mcu_dashboard.models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MovieJdbcTemplateRepository implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public MovieJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    @Transactional
    public Movie findById(int idMovie) {

        final String sql = "select idMovie, title, UsReleaseDate, runtime, ImdbRating, metascore, budget, " +
                "domesticGross, totalGross, openingGross, oscarNominations, oscarsWon, franchise " +
                "from Movie " +
                "where idMovie = ?;";

        Movie movie = jdbcTemplate.query(sql, new MovieMapper(), idMovie).stream()
                .findFirst().orElse(null);

        return movie;
    }

    @Override
    public List<Movie> findAll() {
        final String sql = "select idMovie, title, UsReleaseDate, runtime, ImdbRating, metascore, budget, " +
                "domesticGross, totalGross, openingGross, oscarNominations, oscarsWon, franchise " +
                "from Movie;";
        return jdbcTemplate.query(sql, new MovieMapper());
    }

    @Override
    public Movie add(Movie movie) {

        final String sql = "insert into Movie (title, UsReleaseDate, runtime, ImdbRating, metascore, budget, " +
                "domesticGross, totalGross, openingGross, oscarNominations, oscarsWon, franchise) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movie.getTitle());
            ps.setDate(2, Date.valueOf(movie.getUsReleaseDate()));
            ps.setInt(3, movie.getRuntime());
            ps.setFloat(4, movie.getImdbRating());
            ps.setInt(5, movie.getMetascore());
            ps.setBigDecimal(6, movie.getBudget());
            ps.setBigDecimal(7, movie.getDomesticGross());
            ps.setBigDecimal(8, movie.getTotalGross());
            ps.setBigDecimal(9, movie.getOpeningGross());
            ps.setInt(10, movie.getOscarNominations());
            ps.setInt(11, movie.getOscarsWon());
            ps.setString(12, movie.getFranchise());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        movie.setIdMovie(keyHolder.getKey().intValue());
        return movie;
    }

    @Override
    public boolean update(Movie movie) {

        final String sql = "update Movie set " +
                "title = ?, " +
                "UsReleaseDate = ?, " +
                "runtime = ?, " +
                "ImdbRating = ?, " +
                "metascore = ?, " +
                "budget = ?, " +
                "domesticGross = ?, " +
                "totalGross = ?, " +
                "openingGross = ?, " +
                "oscarNominations = ?, " +
                "oscarsWon = ?, " +
                "franchise = ? " +
                "where idMovie = ?;";

        return jdbcTemplate.update(sql,
                movie.getTitle(),
                movie.getUsReleaseDate(),
                movie.getRuntime(),
                movie.getImdbRating(),
                movie.getMetascore(),
                movie.getBudget(),
                movie.getDomesticGross(),
                movie.getTotalGross(),
                movie.getOpeningGross(),
                movie.getOscarNominations(),
                movie.getOscarsWon(),
                movie.getFranchise(),
                movie.getIdMovie()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int idMovie) {
        return jdbcTemplate.update("delete from Movie where idMovie =?;", idMovie) > 0;
    }
}


