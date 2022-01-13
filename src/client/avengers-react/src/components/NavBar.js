import { Link } from "react-router-dom";
import { useContext } from 'react';

function NavBar() {
  return (
      <>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/about">About</Link>
        </li>
        <li>
          <Link to="/contact">Contact</Link>
        </li>

        <li>
          <Link to="/movies">Movies</Link>
        </li>
        <li>
          <Link to="/movies/add">Add Movie</Link>
        </li>

        <li>
          <Link to="/movie/contributors/1">View Contributors for Ironman</Link>
        </li>
        <li>
          <Link to="/movie/contributors/delete/1/Director/1/Jon Favreau">Delete for Jon Favreau</Link>
        </li>

        <li>
          <Link to="/movie/contributors/add">Add Contributor</Link>
        </li>

        <li>
          <Link to="/login">Login</Link>
        </li>

      </ul>
      </>
  );
}
export default NavBar;