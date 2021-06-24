import React, { useState, useEffect, useContext } from "react";
import BooksTable from "../components/employee/BooksTable";
import BooksSearchBar from "../components/employee/BooksSearchBar";
import { AuthenticationContext } from "../contexts/Authentication";
import { BooksService } from "../api/services/Books";

function Employee() {
  const [user, setUser] = useContext(AuthenticationContext);
  const [books, setBooks] = useState([]);
  const [updateBooks, setUpdateBooks] = useState(false);
  const [searchedBook, setSearchedBook] = useState("");

  const setAllBooks = async () => {
    BooksService.getAll(user)
      .then((value) => {
        setBooks(value);
      })
      .catch((error) => {
        console.error("Could not fetch books! " + error.message);
      });
  };

  const setSearchedBooks = async () => {
    BooksService.searchBook(user, searchedBook)
      .then((value) => {
        setBooks(value);
      })
      .catch((error) => {
        console.error("Could not fetch books! " + error.message);
      });
  };

  useEffect(async () => {
    if (updateBooks) {
      setBooks(books);
      setUpdateBooks(false);
      return;
    }

    if (searchedBook === "") {
      setAllBooks();
    } else {
      setSearchedBooks();
    }
  }, [searchedBook, user, updateBooks]);

  return (
    <div>
      <BooksSearchBar setSearchedBook={setSearchedBook}></BooksSearchBar>
      <BooksTable
        books={books}
        setBooks={setBooks}
        setUpdateBooks={setUpdateBooks}
      ></BooksTable>
    </div>
  );
}

export default Employee;
