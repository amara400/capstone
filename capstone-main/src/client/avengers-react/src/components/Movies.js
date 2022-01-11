import App from './App';
import EditMovie from './EditMovie';
import DeleteMovie from "./DeleteMovie";
import AddMovie from "./AddMovie";
import MovieTable from "./MovieTable";
import React, { useState } from 'react';
import { useEffect } from 'react';
import MovieTable2 from '../MovieTable2';


function Movies(){
    const dataMovie = [
      {idMovie: 1, title: 'Safe', usReleaseDate: 'Hoops', runtime: 'Haven', imdbRating: '1998-09-19', metascore: '69', budget: '69', domesticGross: '69', totalGross: '69', openingGross: '69', oscarNominations: '69', oscarsWon: '69', franchise: '69'},
      {idMovie: 1, title: 'Safe', usReleaseDate: 'Hoops', runtime: 'Haven', imdbRating: '1998-09-19', metascore: '69', budget: '69', domesticGross: '69', totalGross: '69', openingGross: '69', oscarNominations: '69', oscarsWon: '69', franchise: '69'},
  ];
  const intialFormState = { idMovie: null, title: '', usReleaseDate: '', runtime: '', imdbRating: '', metascore: '', budget: '', domesticGross: '', totalGross: '', openingGross: '', oscarNominations: '', oscarsWon: '', franchise: ''};
  const [ movies, setMovies ] = useState(dataMovie);
  const [ editing, setEditing ] = useState(false);
  const [ currentMovie, setCurrentMovie ] = useState(intialFormState);
  const [ adding, setAdding ] = useState(false);
  const [ deleting, setDeleting ] = useState(false);
  
  
  useEffect(() => {
    fetch("http://localhost:8080/api/movie")
      .then(response => {
        if (response.status !== 200){
          return Promise.reject("Movie Fetch Has Failed.")
        }
        return response.json();
      })
      .then(json => setMovies(json))
      .catch(console.log);
  }, []);
  
  const add = (event) => {
    const movie = {
      "idMovie": 0,
      "title": event.title,
      "usReleaseDate": event.usReleaseDate,
      "runtime": event.runtime,
      "imdbRating": event.imdbRating,
      "metascore": event.metascore,
      "budget": event.budget,
      "domesticGross": event.domesticGross,
      "totalGross": event.totalGross,
      "openingGross": event.openingGross,
      "oscarNominations": event.oscarNominations,
      "oscarsWon": event.oscarsWon,
      "franchise": event.franchise
    };


  
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(movie)
    };
  
    fetch("http://localhost:8080/api/movie", init)
    .then(response => {
      if(response.status !== 201){
        return Promise.reject("response isn't 200 OK");
      }
      setAdding(false)
      return response.json();
    })
    .then(json => setMovies([...movies, json]))
    .catch(console.log);
  };
  
  const deleteById = (idMovie) => {
    fetch(`http://localhost:8080/api/movie/${idMovie}`, { method: "DELETE"})
    .then(response => {
      if(response.status === 204){
        setDeleting(false)
        setMovies(movies.filter(s => s.idMovie !== idMovie));
    }else if (response.status === 404){
      return Promise.reject("Movie Not Found.");
    } else{
      return Promise.reject(`Delete Failed with the Status: ${response.status}`);
    }
    })
    .catch(console.log);
  };
  
  const edit = (idMovie, event) => {
    const editedMovie = {
        "idMovie": 0,
        "title": event.title,
        "usReleaseDate": event.usReleaseDate,
        "runtime": event.runtime,
        "imdbRating": event.imdbRating,
        "metascore": event.metascore,
        "budget": event.budget,
        "domesticGross": event.domesticGross,
        "totalGross": event.totalGross,
        "openingGross": event.openingGross,
        "oscarNominations": event.oscarNominations,
        "oscarsWon": event.oscarsWon,
        "franchise": event.franchise
    };
  
    const init = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      }, 
      body: JSON.stringify(editedMovie)
    };
  
    fetch(`http://localhost:8080/api/movie/${idMovie}`, init)
      .then(response => {
        if (response.status === 204){
          setEditing(false)
          setMovies(movies.map(movie => (movie.idMovie === idMovie ? editedMovie : movie)));
        } else if (response.status === 404){
          return Promise.reject("Movie Not Found");
        } else {
          return Promise.reject(`Delete Failed with the Status: ${response.status}`);
        }
      })
  };
  
    const editRow = movie => {
      setEditing(true);
      setCurrentMovie({...movie});
    }
    const addRow = () => {
      setAdding(true);
      setCurrentMovie(intialFormState);
    }
    const deleteRow = movie => {
      setDeleting(true);
      setCurrentMovie({...movie});
    }

    const cancelAdd = movie =>{
        setAdding(false);
        setCurrentMovie({...movie});
    }
  
    return (
      <>
      <div className="container">
        <h1>Marvel Movies</h1>
        <br/>
  
        <div className="row">
          <div className="col">
            {editing ? (
              <>
              <h2>Edit Movie</h2>
              <EditMovie
              editedMovie={edit}
              currentMovie={currentMovie}
              setEditing={setEditing}
              />
              </>
            ) : adding ? (
              <>
              <h2>Add Movie</h2>
              <AddMovie
              AddMovie={add}
              setAdding={cancelAdd}  
              />
              </>
            ) : deleting ? (
              <>
              <h2>Delete Movie</h2>
              <DeleteMovie
              deleteById={deleteById}
              currentMovie={currentMovie}
              setDeleting={setDeleting}
              />
              </>
            ) : (
              <>
              <div className="col">
                <h2>All Movies</h2>
                  <MovieTable2 movies={movies} editRow={editRow} deleteRow={deleteRow} addRow={addRow}
                />
                </div>
                </>
            )}
          </div>
        </div>
      </div>
      </>
    );
    
            }
  export default Movies;