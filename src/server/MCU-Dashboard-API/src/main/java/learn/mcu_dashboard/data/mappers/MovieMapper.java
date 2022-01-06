package learn.mcu_dashboard.data.mappers;

import learn.mcu_dashboard.models.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setIdMovie(resultSet.getInt("idMovie"));
        movie.setTitle(resultSet.getString("title"));
        movie.setUsReleaseDate(resultSet.getDate("UsReleaseDate").toLocalDate());
        movie.setRuntime(resultSet.getInt("runtime"));
        movie.setImdbRating(resultSet.getFloat("ImdbRating"));
        movie.setMetascore(resultSet.getInt("metascore"));
        movie.setBudget(resultSet.getBigDecimal("budget"));
        movie.setDomesticGross(resultSet.getBigDecimal("domesticGross"));
        movie.setTotalGross(resultSet.getBigDecimal("totalGross"));
        movie.setOpeningGross(resultSet.getBigDecimal("openingGross"));
        movie.setOscarNominations(resultSet.getInt("oscarNominations"));
        movie.setOscarsWon(resultSet.getInt("oscarsWon"));
        movie.setFranchise(resultSet.getString("franchise"));

        return movie;
    }
}
