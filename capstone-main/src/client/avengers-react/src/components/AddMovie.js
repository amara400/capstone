import React, { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';


const AddMovie = props => {
    const intialFormState = {
        idMovie: null,
        title: '',
        usReleaseDate: '',
        runtime: '',
        imdbRating: '',
        metascore: '',
        budget: '',
        domesticGross: '',
        totalGross: '',
        openingGross: '',
        oscarNominations: '',
        oscarsWon: '',
        franchise: ''
    };

        const [ movie, setMovie ] = useState(intialFormState);

        const history = useHistory();

        const handleInputChange = event => {
            const { name, value } = event.target;
            setMovie({...movie, [name]: value});
        }

        const add = (event) => {
            event.preventDefault();
            const marvelMovie = {
                "idMovie": 0,
                "title": movie.title,
                "usReleaseDate": movie.usReleaseDate,
                "runtime": movie.runtime,
                "imdbRating": movie.imdbRating,
                "metascore": movie.metascore,
                "budget": movie.budget,
                "domesticGross": movie.domesticGross,
                "totalGross": movie.totalGross,
                "openingGross": movie.openingGross,
                "oscarNominations": movie.oscarNominations,
                "oscarsWon": movie.oscarsWon,
                "franchise": movie.franchise
            };

            const init ={
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(marvelMovie)
            };

            fetch("http://localhost:8080/api/movie", init)
            .then(response => {
                if (response.status !== 201){
                    return Promise.reject("Response is Not 200 OK.");
                }
                setMovie(intialFormState);
                history.push('/movies')
                return response.json();
            })
            .catch(console.log);
        };

        return(
            <form onSubmit={add} className="form-inline mx-2 my-4">
                <label>Movie Title</label>
                <input
                    type="text"
                    class="form-control"
                    name="title"
                    value={movie.title}
                    onChange={handleInputChange}
                    placeholder="Movie Title"
                    required/>
                <br/>
                <label>Movie US Release Date</label>
                <input
                    type="date"
                    class="form-control"
                    name="usReleaseDate"
                    value={movie.usReleaseDate}
                    onChange={handleInputChange}
                    placeholder="Movie US Release Date"
                    required/>
                <br/>
                <label>Movie Runtime</label>
                <input
                    type="text"
                    class="form-control"
                    name="runtime"
                    value={movie.runtime}
                    onChange={handleInputChange}
                    placeholder="Movie Runtime"
                    required/>
                <br/>
                <label>Movie IMDB Rating</label>
                <input
                    type="text"
                    class="form-control"
                    name="imdbRating"
                    value={movie.imdbRating}
                    onChange={handleInputChange}
                    placeholder="Movie IMDB Rating"
                    required/>
                <br/>
                <label>Movie Metascore</label>
                <input
                    type="text"
                    class="form-control"
                    name="metascore"
                    value={movie.metascore}
                    onChange={handleInputChange}
                    placeholder="Movie Metascore"
                    required/>
                <br/>
                <label>Movie Budget</label>
                <input
                    type="text"
                    class="form-control"
                    name="budget"
                    value={movie.budget}
                    onChange={handleInputChange}
                    placeholder="Movie Budget"
                    required/>
                <br/>
                <label>Movie Domestic Gross</label>
                <input
                    type="text"
                    class="form-control"
                    name="domesticGross"
                    value={movie.domesticGross}
                    onChange={handleInputChange}
                    placeholder="Movie Domestic Gross"
                    required/>
                <br/>
                <label>Movie Total Gross</label>
                <input
                    type="text"
                    class="form-control"
                    name="totalGross"
                    value={movie.totalGross}
                    onChange={handleInputChange}
                    placeholder="Movie Total Gross"
                    required/>
                <br/>
                <label>Movie Opening Gross</label>
                <input
                    type="text"
                    class="form-control"
                    name="openingGross"
                    value={movie.openingGross}
                    onChange={handleInputChange}
                    placeholder="Movie Opening Gross"
                    required/>
                <br/>
                <label>Movie Oscar Nominations</label>
                <input
                    type="text"
                    class="form-control"
                    name="oscarNominations"
                    value={movie.oscarNominations}
                    onChange={handleInputChange}
                    placeholder="Movie Oscar Nominations"
                    required/>
                <br/>
                <label>Movie Oscars Won</label>
                <input
                    type="text"
                    class="form-control"
                    name="oscarsWon"
                    value={movie.oscarsWon}
                    onChange={handleInputChange}
                    placeholder="Movie Oscars Won"
                    required/>
                <br/>
                <label>Movie Franchise</label>
                <input
                    type="text"
                    class="form-control"
                    name="franchise"
                    value={movie.franchise}
                    onChange={handleInputChange}
                    placeholder="Movie Franchise"
                    required/>
                <br/>
                <button className="btn btn-primary">Add New Movie</button>
                <button className="btn btn-danger" onClick={() => props.setAdding(false)}>Cancel</button>
            </form>
        )
}

export default AddMovie;