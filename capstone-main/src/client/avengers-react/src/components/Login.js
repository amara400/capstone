import React, {useContext, useState} from "react";
import {Link, useHistory} from "react-router-dom";
import AuthContext from "../context/AuthContext.js";
import jwtDecode from "jwt-decode";

import Errors from "./Errors";

export default function Login(){

    const [username, setUsername] =useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors]=useState("");

    const [authContext, setUserStatus]=useContext(AuthContext);

    const history = useHistory();

    const handleSubmit = function (evt) {
        evt.preventDefault();



    const init ={
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify({username, password})
        };

        fetch("http://localhost:8080/authenticate", init)
        .then(response =>{
            console.log(response);
            if(response.status === 200){
                return response.json()
            }
            else if(response.status === 400){
                const errors = response.json();
                setErrors(errors);
            }
            else if (response.status === 403){
                setErrors(["Login failed."]);
            }
            else{
                setErrors(["Unknown error."])
            }
        })
        .then(json => {
            console.log(json);
            const {jwt_token} = json;
            console.log(jwt_token);
            console.log("Decode");
            console.log(jwtDecode(jwt_token));
            setUserStatus({user: jwtDecode(jwt_token)});
            localStorage.setItem("token", jwt_token);
            history.push("/");
        })
        .catch(console.log);

    }

    console.log("AuthContext");
    console.log(authContext);

    return(
        <div>
            <h2>Login</h2>
            <Errors errors={errors} />
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        onChange={(event) => setUsername(event.target.value)}
                        id="username"
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="text"
                        onChange={(event) => setPassword(event.target.value)}
                        id="password"
                    />
                </div>
                <div>
                    <button type="submit">Login</button>
                </div>
            </form>
            
        </div>
    );

}