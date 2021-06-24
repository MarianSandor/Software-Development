import React, { useState } from "react";
import {
  Navbar,
  Button,
  Nav,
  Form,
  FormControl,
  Dropdown,
} from "react-bootstrap";
import { AuthenticationService } from "../../api/services/Authentication";
import { useHistory } from "react-router-dom";
import { keywordsAdmin } from "../Utils";
import { PartsService } from "../../api/services/Parts";
import { UsersService } from "../../api/services/Users";
import { URLS } from "../../api/Http";

const NavbarComponent = ({
  type,
  setType,
  setParts,
  setConfigurations,
  setAddUserModal,
  setAddPartModal,
}) => {
  let history = useHistory();
  const [search, setSearch] = useState("");

  const logoutHandler = (e) => {
    e.preventDefault();

    AuthenticationService.logout();
    history.push("/");
  };

  const updateSearch = (e) => {
    setSearch(e.target.value);
  };

  const addHandler = () => {
    if (type === "Users") {
      setAddUserModal(true);
    } else {
      setAddPartModal(true);
    }
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
      <Navbar sticky="top" bg="light" expand="lg">
        <Navbar.Brand>PC Part Shop</Navbar.Brand>
        <Dropdown style={{ marginLeft: "20%" }}>
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            {type}
          </Dropdown.Toggle>
          <Dropdown.Menu>
            {keywordsAdmin.map((key) => (
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
            placeholder={
              type === "Users"
                ? "Search by Username/Email"
                : "search by Brand/Model"
            }
            value={search}
            onChange={updateSearch}
            className="mr-sm-2"
            style={{ width: "25rem" }}
          />
          <Button variant="outline-success" type="submit">
            Search
          </Button>
        </Form>
        {type !== "Configuration" && (
          <Button style={{ marginLeft: "8%" }} onClick={addHandler}>
            Add
          </Button>
        )}
        <Button style={{ marginLeft: "10%" }} onClick={logoutHandler}>
          Logout
        </Button>
      </Navbar>
    </div>
  );
};

export default NavbarComponent;
