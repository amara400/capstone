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

            <label>Movie Title: {movie.title}</label>
            <br/>
            <br/>

            <label>Movie US Release Date: {movie.usReleaseDate}</label>
            <br/>
            <br/>

            <label>Movie Runtime: {movie.runtime}</label>
            <br/>
            <br/>
            
            <label>Movie IMDB Rating: {movie.imdbRating}</label>
            <br/>
            <br/>

            <label>Movie Metascore: {movie.metascore}</label>
            <br/>
            <br/>
            
            <label>Movie Budget: {movie.budget}</label>
            <br/>
            <br/>
            
            <label>Movie Domestic Gross: {movie.domesticGross}</label>
            <br/>
            <br/>

            <label>Novie Total Gross: {movie.totalGross}</label>
            <br/>
            <br/>

            <label>Movie Opening Gross: {movie.openingGross}</label>
            <br/>
            <br/>

            <label>Movie Oscar Nominations: {movie.oscarNominations}</label>
            <br/>
            <br/>

            <label>Movie Oscars Won: {movie.oscarsWon}</label>
            <br/>
            <br/>

            <label>Movie Francise: {movie.franchise}</label>
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