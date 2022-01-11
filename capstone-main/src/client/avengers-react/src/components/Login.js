import React, {useState} from "react";
import {Link, useHistory} from "react-router-dom";

import Errors from "./Errors";

export default function Login(){

    const [userName, setUsername] =useState("");
    const[password, setPassword] = useState("");
    const[errors, setErrors]=useState("")

    const history = useHistory();

    const handleSubmit = async (event) => {
        event.preventDefault();


        history.push("/");
    }

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
                        type="password"
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