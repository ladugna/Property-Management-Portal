import React, { useContext } from "react";
import "./Header.css";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "universal-cookie";
import Button from "@mui/material/Button";
import { userGlobalContext } from "../../Context/GlobalContext";

const Header = () => {
  const { user, setUser } = useContext(userGlobalContext);

  const navigate = useNavigate();

  const logout = () => {
    setUser({});
    const cookies = new Cookies();
    cookies.remove("accessToken");
    navigate("/");
  };
  return (
    <div className="header">
      <label>Header</label>
      <Link to={"/"}>
        <Button variant="text">Home</Button>
      </Link>
      {user.role ? (
        <>
          {user.role === 'customer' ? <>
          <Button variant="text" onClick={() => navigate('/offers')}>
            Offers
          </Button>
          </> : null}
          <Button variant="text" onClick={() => logout()}>
            Logout
          </Button>
        </>
      ) : (
        <>
          <Link to={"/login"}>
            <Button variant="text">Login</Button>
          </Link>
          <Link to={"/register"}>
            <Button variant="text">Register</Button>
          </Link>
        </>
      )}
    </div>
  );
};

export default Header;