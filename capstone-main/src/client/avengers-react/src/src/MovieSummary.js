import React from "react";
import {Link} from "react-router-dom"

function MovieSummary(props){
    const handleClick = function(){
        
    }

    console.log(props.movie);

    return(
        <tr key={props.movie.idMovie}>
            <td>{props.movie.title}</td>
            <td>{props.movie.usReleaseDate}</td>
            <td>{props.movie.runtime}</td>
            <td>
                <Link to ={`/movies/data/${props.movie.idMovie}`} className = "btn btn-outline-primary">Full Data</Link>
            </td>
            <td>
                <Link to ={`/movies/edit/${props.movie.idMovie}`} className = "btn btn-outline-warning">Edit</Link>
            </td>
                        
            <td>
                <button
                onClick={() =>{
                    props.deleteRow(props.movie);
                }}
                className = "btn btn-outline-danger">Delete</button>
            </td>
        </tr>
    )

}

export default MovieSummary;