import "./App.css";
import React, { useEffect } from "react";
import Authentication from "./pages/Authentication";
import Employee from "./pages/Employee";
import Customer from "./pages/Customer";
import Administrator from "./pages/Administrator";
import { Route, Switch } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { useHistory } from "react-router-dom";

let user = JSON.parse(localStorage.getItem("user"));

function App() {
  let history = useHistory();

  useEffect(() => {
    if (user !== null) {
      if (user.roles[0] === "CUSTOMER") {
        history.push("/customer");
      } else if (user.roles[0] === "EMPLOYEE") {
        history.push("/employee");
      } else if (user.roles[0] === "ADMIN") {
        history.push("/administrator");
      } else {
        history.push("/");
      }
    } else {
      history.push("/");
    }
  }, [history]);

  return (
    <div className="App">
      <Switch>
        <Route exact path="/" component={Authentication} />
        <Route exact path="/administrator" component={Administrator} />
        <Route exact path="/employee" component={Employee} />
        <Route exact path="/customer" component={Customer} />
      </Switch>
    </div>
  );
}

export default App;
