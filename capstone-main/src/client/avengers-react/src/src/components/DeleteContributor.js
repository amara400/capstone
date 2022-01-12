import React, { useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import Errors from './Errors';


const DeleteContributor = (props) => {
    const [contributor, setContributor] = useState(props.currentContributor);

    const handleSubmit = (event) => {
        event.preventDefault();
        props.deleteById(contributor.idContributor);
    };

    const { idMovie } = useParams();

    const { role } = useParams();

    const { idPerson } = useParams();


    const deleteById = (idMovie, role, idPerson) => {
        fetch(`http://localhost:8080/api/movie_person/${idMovie}/${role}/${idPerson}`
            , { method: "DELETE"})
        .then(response => {
          if(response.status === 204){
            setContributor(contributor.filter(s => s.idPerson !== idPerson));
        }else if (response.status === 404){
          return Promise.reject("Contributor Not Found.");
        } else{
          return Promise.reject(`Delete Failed with the Status: ${response.status}`);
        }
        })
        .catch(console.log);
      };

    return(
        <form onSubmit={handleSubmit}>
            <br/>

            <label><b>Contributor Role:</b> {contributor.role}</label>
            <br/>
            <br/>

            <label><b>Contributor Name:</b> {contributor.person.name}</label>
            <br/>
            <br/>

            <h3>Are you sure you want to delete this contributor?</h3>
            <br/>
            <br/>

            <button type='submit' className = "btn btn-danger" onClick={()=> deleteById(idMovie, role, idPerson)}>Delete</button>
            <button className="btn btn-info">Cancel</button>            
        </form>
    );
};

export default DeleteContributor;