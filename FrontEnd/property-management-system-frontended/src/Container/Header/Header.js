import React, { useContext } from "react";
import "./Header.css";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "universal-cookie";
import { userGlobalContext } from "../../Context/GlobalContext";
import { Button } from "@mui/material";
import { ThemeProvider } from '@emotion/react';
import { styled } from '@mui/system';

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
    <div className="header-container">
      <div className="header">
        <div className="nav-links">
          <Link to={"/"}>
            <Button variant="text">Home</Button>
          </Link>
          {user.role ? (
            <>
              {user.role === "customer" && (
                <Button variant="text" onClick={() => navigate("/offers")}>
                  Offers
                </Button>
              )}
              <Button variant="text" onClick={() => logout()} className="logout-btn">
                Logout
              </Button>
            </>
          ) : (
            <>
              <Link to={"/login"}>
                <Button variant="text" className="login-btn">Login</Button>
              </Link>
              <Link to={"/register"}>
                <Button variant="text" className="register-btn">Register</Button>
              </Link>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default Header;

   

