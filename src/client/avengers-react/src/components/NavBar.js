
import { Link } from "react-router-dom";
import { useContext } from 'react';
import AuthContext from '../context/AuthContext';


function NavBar() {
  const [userStatus, setUserStatus] = useContext(AuthContext);
  console.log("UserStatus");
  console.log(userStatus);
  console.log(userStatus?.user);

  function goHome() {
    console.log("goHome...")
    if(localStorage.getItem('reload')){
      localStorage.removeItem('reload');
  }
  }
  
  return (
      <>
              <nav className="navbar navbar-light" style={{backgroundColor: '#f6f6f6'}}>
      <button className="btn btn-sm btn-outline-danger">          
        <Link to="/" onClick={goHome}>Home</Link>
      </button>
      <button className="btn btn-sm btn-outline-danger">
        <Link to="/about">About</Link>  
      </button>
      <button className="btn btn-sm btn-outline-danger">
        <Link to="/contact">Contact</Link>
      </button>
      <button className="btn btn-sm btn-outline-danger">
        <Link to="/movies">Movies</Link>
      </button>
      {userStatus?.user && (
          <button className="btn btn-sm btn-outline-danger">
            <Link to="/movies/add">Add Movie</Link>
          </button>
        )}
        {userStatus?.user? (
          <li>
            <button className="btn btn-sm btn-outline-danger" onClick={() => {
              setUserStatus(null);
              localStorage.removeItem("token");
            }}
            >
              Logout {userStatus.user.sub}
            </button>
          </li>
        ) : (
          <button className="btn btn-sm btn-outline-danger">
            <Link to="/login">Login</Link>
        </button>
        )}
      </nav>
      <ul className= "list-inline">
        <li className="list-inline-item">
          <Link to="/movie/contributors/1">View Contributors for Ironman</Link>
        </li>
        <li className="list-inline-item">
          <Link to="/movie/contributors/delete/1/Director/1/Jon Favreau">Delete for Jon Favreau</Link>
        </li>
        <li className="list-inline-item">
          <Link to="/movie/contributors/add">Add Contributor</Link>
        </li>
{/* 
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
        )} */}

      </ul>
    </>
  );
      

}
export default NavBar;
