import React from "react";
import { Link } from "react-router-dom";
import App from './App';

const ContributorsTable = (props) => (
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
                        <td>
                            <Link to={`/movie/contributors/delete/${contributor.idMovie}/
                                ${contributor.role}/${contributor.idPerson}/${contributor.person.name}`} 
                                className="btn btn-outline-danger">
                                Delete
                            </Link>
                        </td>
                    </tr>


                ))
            ):(
                <tr>
                    <td colSpan={3}>No Contributors</td>
                </tr>
            )}
            <tr>
            <Link to={`/movie/contributors/add`} className="btn btn-outline-primary">
                Add New Contributor
            </Link>
            </tr>
        </tbody>
    </table>
);

export default ContributorsTable;
