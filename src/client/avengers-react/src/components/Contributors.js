import App from './App';
import DeleteContributor from "./DeleteContributor";
import React, { useState } from 'react';
import { useEffect } from 'react';
import AddContributor from './AddContributor';
import ContributorsTable from './ContributorsTable';
import { Link, useHistory, useParams } from 'react-router-dom';


function Contributors(){
  //   const dataContributors = [
  //     {idMovie: 1, role: 'Director', idPerson: 1, person: {idPerson: 1, name: 'Eden'}},
  //     {idMovie: 2, role: 'Producer', idPerson: 2, person: {idPerson: 2, name: 'Haven'}},
  // ];
  const intialFormState = {
    idMovie: null, 
    role: '', 
    idPerson: null, 
    person: {
      idPerson: null, 
      name: ''
    },
  };
  const [ contributors, setContributors ] = useState([]);

  const [ currentContributor, setCurrentContributor ] = useState(intialFormState);
  const [ adding, setAdding ] = useState(false);
  const [ deleting, setDeleting ] = useState(false)
  const { idMovie } = useParams();
;
  
  
  useEffect(() => {
    fetch(`http://localhost:8080/api/movie_person/${idMovie}`)
      .then(response => {
        if (response.status !== 200){
          return Promise.reject("Contributor Fetch Has Failed.")
        }
        return response.json();
      })
      .then(json => setContributors(json))
      .catch(console.log);
  }, []);
  
  const add = (event) => {
    const contributor = {
      "idMovie": 0,
      "role": event.role,
      "idPerson": 0,
      "person": {
        "idPerson": 0,
        "name": event.name,
    }};
  
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(contributor)
    };
  
    fetch("http://localhost:8080/api/contributor", init)
    .then(response => {
      if(response.status !== 201){
        return Promise.reject("response isn't 200 OK");
      }
      setAdding(false)
      return response.json();
    })
    .then(json => setContributors([...contributors, json]))
    .catch(console.log);
  };
  
  const deleteById = (idPerson) => {
    fetch(`http://localhost:8080/api/contributor/${idPerson}`, { method: "DELETE"})
    .then(response => {
      if(response.status === 204){
        setDeleting(false)
        setContributors(contributors.filter(s => s.idPerson !== idPerson));
    }else if (response.status === 404){
      return Promise.reject("Contributor Not Found.");
    } else{
      return Promise.reject(`Delete Failed with the Status: ${response.status}`);
    }
    })
    .catch(console.log);
  };
  
    const addRow = () => {
      setAdding(true);
      setCurrentContributor(intialFormState);
    }
    // const deleteRow = contributor => {
    //   setDeleting(true);
    //   setCurrentContributor({...contributor});
    // }

    const deleteRow = contributor => {
      return(
              <DeleteContributor />);
    }

    const cancelAdd = contributor =>{
        setAdding(false);
        setCurrentContributor({...contributor});
    }
  
    // return (
    //   <>
    //   <div className="container">
    //     <h1>Marvel Contributors</h1>
    //     <br/>
  
    //     <div className="row">
    //       <div className="col">
    //         {adding ? (
    //           <>
    //           <h2>Add Contributor</h2>
    //           <AddContributor
    //           AddContributor={add}
    //           setAdding={cancelAdd}  
    //           />
    //           </>
    //         ) : deleting ? (
    //           <>
    //           <h2>Delete Contributor</h2>
    //           <DeleteMovie
    //           deleteById={deleteById}
    //           currentContributor={currentContributor}
    //           setDeleting={setDeleting}
    //           />
    //           </>
    //         ) : (
    //           <>
    //           <div className="col">
    //             <h2>All Contributors</h2>
    //               <ContributorsTable contributors={contributors} deleteRow={deleteRow} addRow={addRow}
    //             />
    //             </div>
    //             </>
    //         )}

    //       </div>
    //     </div>
    //   </div>
    //   </>
    // );
    return (
      <>
      <div className="container">
        <h1>Marvel Contributors</h1>
        <br/>
  
        <div className="row">
          {}
              <>
              <div className="col">
                <h2>All Contributors</h2>
                  <ContributorsTable contributors={contributors} deleteRow={deleteRow} addRow={addRow}
                />
                </div>
                </>
        </div>
      </div>
      </>
    );
    
            }
  export default Contributors;