import React, { useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import Errors from './Errors';
import ContributorsData from './ContributorsData';

const FullMovieData = (props) => {
    const dataMovie = {idMovie: 1, title: 'Safe', usReleaseDate: 'Hoops', runtime: 'Haven', imdbRating: '1998-09-19', metascore: '69', budget: '69', domesticGross: '69', totalGross: '69', openingGross: '69', oscarNominations: '69', oscarsWon: '69', franchise: '69'};
    const [movie, setMovie] = useState(dataMovie);


    const { idMovie } = useParams();
    const history = useHistory();

    useEffect(() => {
        fetch(`http://localhost:8080/api/movie/${idMovie}`)
            .then(response => {
                if (response.status === 404){
                    return Promise.reject(`Received 404 - Not Found for Movie ID: ${idMovie}`);
                }
                return response.json();
            })
            .then(movie => {
                setMovie(movie);
            }
            )
            .catch(error => {
                console.log(error);
            });
    }, [idMovie]);

    return(
        <div>
            <br/>

            <h1 className='display-4'><b>{movie.title}</b> </h1>
            <br/>
            <div className='row'>
                <div className='col'>
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
                </div>
                <div className='col'>
                    <ContributorsData />
                </div>
            </div>
            

            
        </div>
    );
};

export default FullMovieData;