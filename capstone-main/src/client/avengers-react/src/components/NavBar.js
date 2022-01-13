import { Link } from "react-router-dom";
import { useContext } from 'react';
import AuthContext from '../context/AuthContext'

function NavBar() {
  const [userStatus, setUserStatus] = useContext(AuthContext);
  console.log("UserStatus");
  console.log(userStatus);
  console.log(userStatus?.user);

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
        {userStatus?.user && (
          <li>
            <Link to="/movies/add">Add Movie</Link>
          </li>
        )}
        {userStatus?.user? (
          <li>
            <button onClick={() => {
              setUserStatus(null);
              localStorage.removeItem("token");
            }}
            >
              Logout {userStatus.user.sub}
            </button>
          </li>
        ) : (
          <li>
            <Link to="/login">Login</Link>
        </li>
        )}
      </ul>
      </>
  );
}
export default NavBar;