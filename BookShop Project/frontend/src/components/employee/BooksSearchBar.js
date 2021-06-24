import React, { useState, useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Navbar, Button, Nav, Form, FormControl } from "react-bootstrap";
import { AuthenticationContext } from "../../contexts/Authentication";

const BooksSearchBar = ({ setSearchedBook }) => {
  const [user, setUser] = useContext(AuthenticationContext);
  const [search, setSearch] = useState("");

  const updateSearch = (e) => {
    setSearch(e.target.value);
  };

  const searchHandler = (e) => {
    e.preventDefault();

    setSearchedBook(search);
    setSearch("");
  };

  return (
    <div>
      <Navbar sticky="top" bg="light" expand="lg">
        <Navbar.Brand>Book Store</Navbar.Brand>
        <Nav
          className="justify-content-center"
          style={{ marginLeft: "25%" }}
        ></Nav>
        <Form inline onSubmit={searchHandler}>
          <FormControl
            type="text"
            placeholder="search by Title/Author/Genre"
            value={search}
            onChange={updateSearch}
            className="mr-sm-2"
            style={{ width: "25rem" }}
          />
          <Button variant="outline-success" type="submit">
            Search
          </Button>
        </Form>
        <Button style={{ marginLeft: "25%" }} onClick={() => setUser({})}>
          Logout
        </Button>
      </Navbar>
    </div>
  );
};

export default BooksSearchBar;
