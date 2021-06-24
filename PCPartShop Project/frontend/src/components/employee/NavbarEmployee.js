import React, { useState } from "react";
import {
  Dropdown,
  Navbar,
  Button,
  Nav,
  Form,
  FormControl,
} from "react-bootstrap";
import { AuthenticationService } from "../../api/services/Authentication";
import { useHistory } from "react-router-dom";
import { keywords } from "../Utils";
import { PartsService } from "../../api/services/Parts";
import { UsersService } from "../../api/services/Users";
import { WebSocketConnection } from "../../api/WebSocketConnection";
import { URLS } from "../../api/Http";

function NavbarEmployee({ type, setType, setParts, setConfigurations }) {
  let history = useHistory();
  const [search, setSearch] = useState("");

  const logoutHandler = (e) => {
    e.preventDefault();

    AuthenticationService.logout();
    WebSocketConnection.disconnect();

    history.push("/");
  };

  const updateSearch = (e) => {
    setSearch(e.target.value);
  };

  const searchHandler = (e) => {
    e.preventDefault();
    let user = JSON.parse(localStorage.getItem("user"));

    if (type === "Configuration") {
      UsersService.getUserConfigurations(user.id)
        .then((value) => {
          setConfigurations(value);
        })
        .catch((error) => {
          console.error("Could not fetch Configurations! " + error.message);
        });
    } else {
      PartsService.getAllBy(URLS[type], search)
        .then((value) => {
          setParts(value);
        })
        .catch((error) => {
          console.error("Could not fetch Parts! " + error.message);
        });
    }

    setSearch("");
  };
  return (
    <div style={{ position: "sticky", top: "0" }}>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand>PC Part Shop</Navbar.Brand>
        <Dropdown style={{ marginLeft: "20%" }}>
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            {type}
          </Dropdown.Toggle>
          <Dropdown.Menu>
            {keywords.map((key) => (
              <Dropdown.Item
                onClick={() => {
                  setType(key);
                }}
                key={key}
              >
                {key}
              </Dropdown.Item>
            ))}
          </Dropdown.Menu>
        </Dropdown>
        <Nav
          className="justify-content-center"
          style={{ marginLeft: "1%" }}
        ></Nav>
        <Form inline onSubmit={searchHandler}>
          <FormControl
            type="text"
            placeholder="search by Brand/Model"
            value={search}
            onChange={updateSearch}
            className="mr-sm-2"
            style={{ width: "25rem" }}
          />
          <Button variant="outline-success" type="submit">
            Search
          </Button>
        </Form>

        <Button style={{ marginLeft: "20%" }} onClick={logoutHandler}>
          Logout
        </Button>
      </Navbar>
    </div>
  );
}

export default NavbarEmployee;
