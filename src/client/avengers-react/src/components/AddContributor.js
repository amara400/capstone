import React, { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';


const AddContributor = props => {
    const intialFormState = {
        idMovie: null, 
        role: '', 
        idPerson: null, 
        person: {
          idPerson: null, 
          name: ''
        },
    };

        const [ contributor, setContributor ] = useState(intialFormState);

        const history = useHistory();

        const handleInputChange = event => {
            const { name, value } = event.target;
            setContributor({...contributor, [name]: value});
        }

        const add = (event) => {
            event.preventDefault();
            const moviePerson = {
                idMovie: 0, 
                role: null, 
                idPerson: 0, 
                person: {
                  idPerson: 0, 
                  name: null
                },
            };

            const init ={
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(moviePerson)
            };

            fetch("http://localhost:8080/api/movie", init)
            .then(response => {
                if (response.status !== 201){
                    return Promise.reject("Response is Not 200 OK.");
                }
                setContributor(intialFormState);
                history.push('/movies')
                return response.json();
            })
            .catch(console.log);
        };

        return(
            <form onSubmit={add} className="form-inline mx-2 my-4">
                <label>Contributor Role</label>
                <input
                    type="text"
                    class="form-control"
                    name="role"
                    value={contributor.role}
                    onChange={handleInputChange}
                    placeholder="role"
                    required/>
                <br/>
                <label>Contributor Name</label>    
                <input
                    type="text"
                    class="form-control"
                    name="name"
                    value={contributor.person.name}
                    onChange={handleInputChange}
                    placeholder="name"
                    required/>
                <br/>    
                <button className="btn btn-primary">Add New Contributor</button>
                <button className="btn btn-danger" onClick={() => props.setAdding(false)}>Cancel</button>
            </form>
        )
}

export default AddContributor;