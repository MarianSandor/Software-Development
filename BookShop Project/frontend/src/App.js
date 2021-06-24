import "./App.css";
import React, { useContext } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import Authentication from "./pages/Authentication";
import Employee from "./pages/Employee";
import Administrator from "./pages/Administrator";
import { AuthenticationContext } from "./contexts/Authentication";

function App() {
  const [user, setUser] = useContext(AuthenticationContext);

  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/" exact>
            <Authentication />
          </Route>
          <Route path="/employee" exact>
            {Object.keys(user).length === 0 ? (
              <Redirect to="/" />
            ) : (
              <Employee />
            )}
          </Route>
          <Route path="/administrator" exact>
            {Object.keys(user).length === 0 ? (
              <Redirect to="/" />
            ) : (
              <Administrator />
            )}
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
