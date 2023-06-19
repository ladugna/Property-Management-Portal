
import { Route, Routes } from "react-router-dom";
import PublicPage from "../Publicpage/PublicPage";
import LoginPage from "../HomePage/Loginpage";
import RegisterPage from "../HomePage/Registerpage";

const Routing = () => {
  return (
    <Routes>
      <Route path="/" element={<PublicPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage/>} />
    </Routes>
  );
};

export default Routing;
