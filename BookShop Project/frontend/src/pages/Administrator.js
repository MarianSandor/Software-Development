import React, { useState, useEffect, useContext } from "react";
import BooksTable from "../components/administrator/BooksTable";
import BooksSearchBar from "../components/administrator/BooksSearchBar";
import UsersTable from "../components/administrator/UsersTable";
import UsersSearchBar from "../components/administrator/UsersSearchBar";
import AddBook from "../components/modals/AddBook";
import AddUser from "../components/modals/AddUser";
import UpdateBook from "../components/modals/UpdateBook";
import UpdateUser from "../components/modals/UpdateUser";
import { AuthenticationContext } from "../contexts/Authentication";
import { BooksService } from "../api/services/Books";
import { UsersService } from "../api/services/Users";

function Administrator() {
  const [user, setUser] = useContext(AuthenticationContext);
  const [usersBooksUI, setUsersBooksUI] = useState(true);
  const [books, setBooks] = useState([]);
  const [searchedBook, setSearchedBook] = useState("");
  const [users, setUsers] = useState([]);
  const [searchedUser, setSearchedUser] = useState("");
  const [addBookModal, setAddBookModal] = useState(false);
  const [addUserModal, setAddUserModal] = useState(false);
  const [updateBookModal, setUpdateBookModal] = useState(false);
  const [updateUserModal, setUpdateUserModal] = useState(false);
  const [bookToUpdate, setBookToUpdate] = useState({});
  const [userToUpdate, setUserToUpdate] = useState({});
  const [updateBooks, setUpdateBooks] = useState(false);
  const [updateUsers, setUpdateUsers] = useState(false);

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

  const setAllUsers = async () => {
    UsersService.getAll(user)
      .then((value) => {
        setUsers(value);
      })
      .catch((error) => {
        console.error("Could not fetch users! " + error.message);
      });
  };

  const setSearchedUsers = async () => {
    UsersService.searchUser(user, searchedUser)
      .then((value) => {
        setUsers(value);
      })
      .catch((error) => {
        console.error("Could not fetch users! " + error.message);
      });
  };

  useEffect(async () => {
    if (updateUsers) {
      setUsers(users);
      setUpdateUsers(false);
    }
    if (updateBooks) {
      setBooks(books);
      setUpdateBooks(false);
    }

    if (usersBooksUI) {
      if (searchedUser === "") {
        setAllUsers();
      } else {
        setSearchedUsers();
      }
      setSearchedBook("");
    } else {
      if (searchedBook === "") {
        setAllBooks();
      } else {
        setSearchedBooks();
      }
      setSearchedUser("");
    }
  }, [
    searchedBook,
    searchedUser,
    usersBooksUI,
    user,
    updateBooks,
    updateUsers,
  ]);

  return (
    <>
      <div>
        {usersBooksUI ? (
          <div>
            <UsersSearchBar
              setUsersBooksUI={setUsersBooksUI}
              setSearchedUser={setSearchedUser}
              setAddUserModal={setAddUserModal}
            ></UsersSearchBar>
            <UsersTable
              users={users}
              setUsers={setUsers}
              setUpdateUsers={setUpdateUsers}
              setUserToUpdate={setUserToUpdate}
              setUpdateUserModal={setUpdateUserModal}
            ></UsersTable>
          </div>
        ) : (
          <div>
            <BooksSearchBar
              setUsersBooksUI={setUsersBooksUI}
              setSearchedBook={setSearchedBook}
              setAddBookModal={setAddBookModal}
            ></BooksSearchBar>
            <BooksTable
              books={books}
              setBooks={setBooks}
              setUpdateBooks={setUpdateBooks}
              setBookToUpdate={setBookToUpdate}
              setUpdateBookModal={setUpdateBookModal}
            ></BooksTable>
          </div>
        )}
      </div>
      <AddBook
        addBookModal={addBookModal}
        setAddBookModal={setAddBookModal}
        setUpdateBooks={setUpdateBooks}
      />
      <AddUser
        addUserModal={addUserModal}
        setAddUserModal={setAddUserModal}
        setUpdateUsers={setUpdateUsers}
      />
      <UpdateBook
        updateBookModal={updateBookModal}
        setUpdateBookModal={setUpdateBookModal}
        setUpdateBooks={setUpdateBooks}
        bookToUpdate={bookToUpdate}
      />
      <UpdateUser
        updateUserModal={updateUserModal}
        setUpdateUserModal={setUpdateUserModal}
        setUpdateUsers={setUpdateUsers}
        userToUpdate={userToUpdate}
      />
    </>
  );
}

export default Administrator;
