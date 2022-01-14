import React from "react";
import { Link } from "react-router-dom";
import App from './App';

const ContributorsTableNoCRUD = (props) => (
    <table className = "table table-striped table-darl table-hover">
        <thead light>
            <tr>
                <th>Role</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            {props.contributors.length > 0 ? (
                props.contributors.map((contributor) => (
                    <tr key={contributor.idContributor}>
                        <td>{contributor.role}</td>
                        <td>{contributor.person.name}</td>
                    </tr>
                ))
            ):(
                <tr>
                    <td colSpan={3}>No Contributors</td>
                </tr>
            )}
            <tr>
            </tr>
        </tbody>
    </table>
);

export default ContributorsTableNoCRUD;
