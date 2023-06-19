
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import DashBoard from './DashBoard/DashBoard';
import { userGlobalContext } from './Context/GlobalContext';
import { useState } from 'react';

function App() {

  const [user, setUser] = useState({
    isAuthenticated: false,
    id: null,
  });
  return (
    <BrowserRouter>
        <userGlobalContext.Provider value={{user, setUser}}>
        <h1> Welcome WAA </h1>
        <DashBoard />
        </userGlobalContext.Provider>
      </BrowserRouter>
  );
}
//

export default App;
