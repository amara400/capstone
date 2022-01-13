import React, { useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';
import Errors from './Errors';


const DeleteContributor = (props) => {
    const [contributor, setContributor] = useState(props.currentContributor);

    const { idMovie } = useParams();
    const { role } = useParams();
    const { idPerson } = useParams();
    const { name } = useParams();

    const handleSubmit = (event) => {
        event.preventDefault();
        deleteByKey(idMovie, role, idPerson);
    };

    const deleteByKey = (idMovie, role, idPerson) => {
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
            <h3>Contributor Information</h3>
            <br></br>
              <table className = "table table-striped table-darl table-hover">
                <tbody>
                  <tr><td><b>Contributor Role:</b></td><td>{role}</td></tr>
                  <tr><td><b>Contributor Name:</b></td><td>{name}</td></tr>
                </tbody>
              </table>
            <br></br>
            <h4>Are you sure you want to delete this contributor?</h4>

            <button type='submit' className = "btn btn-danger" onClick={()=> deleteByKey(idMovie, role, idPerson)}>Delete</button>
            <Link to={`/movie/contributors/${idMovie}`} className="btn btn-primary btn-med">
                <i className="bi bi-pencil"></i> Cancel
            </Link>         
        </form>
    );
};

export default DeleteContributor;