
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
          <Link to="/home" onClick={goHome}>Home</Link>
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
              <button className="btn btn-sm btn-outline-danger" onClick={() => {
                setUserStatus(null);
                localStorage.removeItem("token");
              }}
              >
                Logout {userStatus.user.sub}
              </button>
          ) : (
            <button className="btn btn-sm btn-outline-danger">
              <Link to="/login">Login</Link>
          </button>
          )}
        </nav>
    </>
  );
      

}
export default NavBar;
