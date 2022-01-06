import React from "react";
import { Link } from "react-router-dom";
import App from './App';

const MovieTable = (props) => (
    <table className = "table table-striped table-darl table-hover">
        <thead light>
            <tr>
                <th>Title</th>
                <th>US Release Date</th>
                <th>Runtime</th>
                <th>IMDB Rating</th>
                <th>Metascore</th>
                <th>Budget</th>
                <th>Domestic Gross</th>
                <th>Total Gross</th>
                <th>Opening Gross</th>
                <th>Oscar Nominations</th>
                <th>Oscars Won</th>
                <th>Franchise</th>
            </tr>
        </thead>
        <tbody>
            {props.movies.length > 0 ? (
                props.movies.map((movie) => (
                    <tr key={movie.idMovie}>
                        <td>{movie.title}</td>
                        <td>{movie.UsReleaseDate}</td>
                        <td>{movie.runtime}</td>
                        <td>{movie.ImdbRating}</td>
                        <td>{movie.metascore}</td>
                        <td>{movie.budget}</td>
                        <td>{movie.domesticGross}</td>
                        <td>{movie.totalGross}</td>
                        <td>{movie.openingGross}</td>
                        <td>{movie.oscarNominations}</td>
                        <td>{movie.oscarsWon}</td>
                        <td>{movie.franchise}</td>
                        <td></td>
                        <Link to ={`/movies/edit/${movie.idMovie}`} className = "btn btn-outline-warning">Edit</Link>
                        <td>
                            <button
                            onClick={() =>{
                                props.deleteMovie(movie);
                            }}
                            className = "btn btn-outline-danger">Delete</button>
                        </td>
                        </tr>
                ))
            ):(
                <tr>
                    <td colSpan={3}>No Movies</td>
                    </tr>
            )}
        </tbody>
        </table>
);

export default MovieTable;
