import React, { useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import Errors from './Errors';

const EditMovie = () => {
    const [title, setTitle] = useState('');
    const [UsReleaseDate, setUsReleaseDate] = useState('');
    const [runtime, setRuntime] = useState('');
    const [ImdbRating, setImdbRating] = useState('');
    const [metascore, setMetascore] = useState('');
    const [budget, setBudget] = useState('');
    const [domesticGross, setDomesticGross] = useState('');
    const [totalGross, setTotalGross] = useState('');
    const [openingGross, setOpeningGross] = useState('');
    const [oscarNominations, setOscarNominations] = useState('');
    const [oscarsWon, setOscarsWon] = useState('');
    const [franchise, setFranchise] = useState('');


    const { idMovie } = useParams();
    const history = useHistory();

    const titleOnChangeHandler = (event) => {
        setTitle(event.target.value);
    };
    const UsReleaseDateOnChangeHandler = (event) => {
        setUsReleaseDate(event.target.value);
    };
    const runtimeOnChangeHandler = (event) => {
        setRuntime(event.target.value);
    };
    const ImdbRatingOnChangeHandler = (event) => {
        setImdbRating(event.target.value);
    };
    const metascoreOnChangeHandler = (event) => {
        setMetascore(event.target.value);
    };
    const budgetOnChangeHandler = (event) => {
        setBudget(event.target.value);
    };
    const domesticGrossOnChangeHandler = (event) => {
        setDomesticGross(event.target.value);
    };
    const totalGrossOnChangeHandler = (event) => {
        setTotalGross(event.target.value);
    };
    const openingGrossOnChangeHandler = (event) => {
        setOpeningGross(event.target.value);
    };
    const oscarNominationsOnChangeHandler = (event) => {
        setOscarNominations(event.target.value);
    };
    const oscarsWonOnChangeHandler = (event) => {
        setOscarsWon(event.target.value);
    };
    const franchiseOnChangeHandler = (event) => {
        setFranchise(event.target.value);
    };


    useEffect(() => {
        fetch(`http://localhost:8080/api/movie/${idMovie}`)
            .then(response => {
                if (response.status === 404){
                    return Promise.reject(`Received 404 - Not Found for Movie ID: ${idMovie}`);
                }
                return response.json();
            })
            .then(data => {
                setTitle(data.title);
                setUsReleaseDate(data.UsReleaseDate);
                setRuntime(data.runtime);
                setImdbRating(data.ImdbRating);
                setMetascore(data.metascore);
                setBudget(data.budget);
                setDomesticGross(data.domesticGross);
                setTotalGross(data.totalGross);
                setOpeningGross(data.openingGross);
                setOscarNominations(data.oscarNominations);
                setOscarsWon(data.oscarsWon);
                setFranchise(data.franchise);
            })
            .catch(error => {
                console.log(error);
            });
    }, [idMovie]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const editedMovie = {
            "idMovie": idMovie,
            "title": title,
            "UsReleaseDate": UsReleaseDate,
            "runtime": runtime,
            "ImdbRating": ImdbRating,
            "metascore": metascore,
            "budget": budget,
            "domesticGross": domesticGross,
            "totalGross": totalGross,
            "openingGross": openingGross,
            "oscarNominations": oscarNominations,
            "oscarsWon": oscarsWon,
            "franchise": franchise,
        };
        const init ={
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editedMovie)
        };
        fetch(`http://localhost:8080/api/movie/${editedMovie.idMovie}`, init)
            .then(response => {
                if (response.status === 204){
                    return null;
                } else if (response.status === 400){
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong.');
            })
            .then(data => {
                if (!data) {
                    history.push('/movies');
                }
            })
            .catch(error => console.log(error));
    }
    return(
        <>
        <h2 className="my-4">Edit Movie</h2>
        <form onSubmit={handleSubmit}>
            <div className="form-group">
            <label htmlFor="title">Movie Title: </label>
            <input className="form-control"
            type="text" 
            id="title"
            name="title" 
            value={title}
            onChange={titleOnChangeHandler}
            />
            </div>
            <br/>

            <div className="form-group">
            <label htmlFor="UsReleaseDate">Movie US Release Date: </label>
            <input className="form-control"
            type="date" 
            id="UsReleaseDate"
            name="UsReleaseDate" 
            value={UsReleaseDate}
            onChange={UsReleaseDateOnChangeHandler}
            />
            </div>
            <br/>

            <div className="form-group">
            <label htmlFor="runtime">Movie Runtime: </label>
            <input className="form-control"
            type="text" 
            id="runtime"
            name="runtime" 
            value={runtime}
            onChange={runtimeOnChangeHandler}
            />
            </div>
            <br/>

            <div className="form-group">
            <label htmlFor="ImdbRating">Movie IMDB Rating: </label>
            <input className="form-control"
            type="text" 
            id="ImdbRating"
            name="ImdbRating" 
            value={ImdbRating}
            onChange={ImdbRatingOnChangeHandler}
            />
            </div>
            <br/>

            <div className="form-group">
            <label htmlFor="metascore">Movie Metascore: </label>
            <input className="form-control"
            type="text" 
            id="metascore"
            name="metascore" 
            value={metascore}
            onChange={metascoreOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="budget">Movie Budget: </label>
            <input className="form-control"
            type="text" 
            id="budget"
            name="budget" 
            value={budget}
            onChange={budgetOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="domesticGross">Movie Domestic Gross: </label>
            <input className="form-control"
            type="text" 
            id="domesticGross"
            name="domesticGross" 
            value={domesticGross}
            onChange={domesticGrossOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="totalGross">Movie Total Gross: </label>
            <input className="form-control"
            type="text" 
            id="totalGross"
            name="totalGross" 
            value={totalGross}
            onChange={totalGrossOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="openingGross">Movie Opening Gross: </label>
            <input className="form-control"
            type="text" 
            id="openingGross"
            name="openingGross" 
            value={openingGross}
            onChange={openingGrossOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="oscarNominations">Movie Oscar Nominations: </label>
            <input className="form-control"
            type="text" 
            id="oscarNominations"
            name="oscarNominations" 
            value={oscarNominations}
            onChange={oscarNominationsOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="oscarsWon">Movie Oscars Won: </label>
            <input className="form-control"
            type="text" 
            id="oscarsWon"
            name="oscarsWon" 
            value={oscarsWon}
            onChange={oscarsWonOnChangeHandler}
            />
            </div>
            <br/>
            
            <div className="form-group">
            <label htmlFor="franchise">Movie Franchise: </label>
            <input className="form-control"
            type="text" 
            id="franchise"
            name="franchise" 
            value={franchise}
            onChange={franchiseOnChangeHandler}
            />
            </div>
            <br/>

            <div className="mt-5">
                <button className="btn btn-success"
                type="submit">
                    <i className="bi bi-save"></i>Edit Movies</button>
                    <Link to="/movies" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i>Cancel</Link>
                </div>
                </form>
                </>
    );
}

export default EditMovie;