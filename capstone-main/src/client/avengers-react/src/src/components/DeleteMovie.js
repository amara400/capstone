import React, { useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import Errors from './Errors';

const DeleteMovie = (props) => {
    const [movie, setMovie] = useState(props.currentMovie);

    const handleSubmit = (event) => {
        event.preventDefault();
        props.deleteById(movie.idMovie);
    };
    return(
        <form onSubmit={handleSubmit}>
            <br/>

            <label><b>Movie Title:</b> {movie.title}</label>
            <br/>
            <br/>

            <label><b>Movie US Release Date:</b> {movie.usReleaseDate}</label>
            <br/>
            <br/>

            <label><b>Movie Runtime (min):</b> {movie.runtime}</label>
            <br/>
            <br/>
            
            <label><b>Movie IMDB Rating:</b> {movie.imdbRating.toLocaleString(undefined, {minimumFractionDigits: 1, maximumFractionDigits: 1})}</label>
            <br/>
            <br/>

            <label><b>Movie Metascore:</b> {movie.metascore}</label>
            <br/>
            <br/>
            
            <label><b>Movie Budget:</b> ${movie.budget.toLocaleString()}</label>
            <br/>
            <br/>
            
            <label><b>Movie Domestic Gross:</b> ${movie.domesticGross.toLocaleString()}</label>
            <br/>
            <br/>

            <label><b>Movie Total Gross:</b> ${movie.totalGross.toLocaleString()}</label>
            <br/>
            <br/>

            <label><b>Movie Opening Gross:</b> ${movie.openingGross.toLocaleString()}</label>
            <br/>
            <br/>

            <label><b>Movie Oscar Nominations:</b> {movie.oscarNominations}</label>
            <br/>
            <br/>

            <label><b>Movie Oscars Won:</b> {movie.oscarsWon}</label>
            <br/>
            <br/>

            <label><b>Movie Francise:</b> {movie.franchise}</label>
            <br/>
            <br/>

            <h3>Are you sure you want to delete this movie?</h3>
            <br/>
            <br/>

            <button type='submit' className = "btn btn-danger" onClick={()=> props.setDeleting(true)}>Delete</button>
            <button className="btn btn-info" onClick={() => props.setDeleting(false)}>Cancel</button>            
        </form>
    );
};

export default DeleteMovie;