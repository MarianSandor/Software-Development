import React, { useState, useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Navbar, Button, Nav, Form, FormControl } from "react-bootstrap";
import { AuthenticationContext } from "../../contexts/Authentication";
import { BooksService } from "../../api/services/Books";

const BooksSearchBar = ({
  setUsersBooksUI,
  setSearchedBook,
  setAddBookModal,
}) => {
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

  const downloadCSV = (data) => {
    const blob = new Blob([data], { type: "text/csv" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.setAttribute("hidden", "");
    a.setAttribute("href", url);
    a.setAttribute("download", "report.csv");
    document.body.appendChild(a);
    a.click();
  };

  const downloadPDF = (data) => {
    const blob = new Blob([data], { type: "application/pdf" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.setAttribute("hidden", "");
    a.setAttribute("href", url);
    a.setAttribute("download", "report.pdf");
    document.body.appendChild(a);
    a.click();
  };

  const exportHandler = (type) => {
    BooksService.report(user, type)
      .then((value) => {
        if (type === "CSV") downloadCSV(value);
        else if (type === "PDF") downloadPDF(value);
      })
      .catch((error) => {
        console.error("Could not export report! " + error.message);
      });
  };

  return (
    <div>
      <Navbar sticky="top" bg="light" expand="lg">
        <Navbar.Brand>Book Store</Navbar.Brand>
        <Nav
          className="justify-content-center"
          style={{ marginLeft: "15%" }}
        ></Nav>
        <Button
          style={{ marginRight: "7%" }}
          onClick={() => setUsersBooksUI(true)}
        >
          Users
        </Button>
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
        <Button
          style={{ marginLeft: "10px" }}
          onClick={() => setAddBookModal(true)}
        >
          Add
        </Button>
        <Button
          style={{ marginLeft: "2%" }}
          onClick={() => exportHandler("PDF")}
        >
          Export PDF
        </Button>
        <Button
          style={{ marginLeft: "1%" }}
          onClick={() => exportHandler("CSV")}
        >
          Export CSV
        </Button>
        <Button style={{ marginLeft: "5%" }} onClick={() => setUser({})}>
          Logout
        </Button>
      </Navbar>
    </div>
  );
};

export default BooksSearchBar;
