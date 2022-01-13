import React from "react";
import MovieSummary from "./MovieSummary";

function MovieTable2(props){


    return(
        <table className = "table table-striped table-darl table-hover">
            <thead light>
                <th>Title</th>
                <th>US Release Date</th>
                <th>Runtime</th>
            </thead>
            <tbody>
            {props.movies.length > 0 ? (
                props.movies.map((movie) => (
                    <MovieSummary movie={movie}  deleteRow={props.deleteRow}/>
                    ))
            ):(
                <tr>
                    <td colSpan={3}>No Movies</td>
                    </tr>
            )}
            </tbody>
        </table>
    )
}

export default MovieTable2;