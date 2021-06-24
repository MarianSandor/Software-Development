import React, { useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Table, Button } from "react-bootstrap";
import { BooksService } from "../../api/services/Books";
import { AuthenticationContext } from "../../contexts/Authentication";

const BooksTable = ({
  books,
  setBooks,
  setUpdateBooks,
  setBookToUpdate,
  setUpdateBookModal,
}) => {
  const [user, setUser] = useContext(AuthenticationContext);

  const deleteHandler = (book) => {
    let updatedBooks = books;

    BooksService.deleteBook(user, book.id)
      .then((value) => {
        let index = updatedBooks.indexOf(book);
        updatedBooks.splice(index);

        setBooks(updatedBooks);
        setUpdateBooks(true);
      })
      .catch((error) => {
        console.error("Could not delete book! " + error.message);
      });
  };

  return (
    <div>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Price</th>
            <th>Quantity</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.genre}</td>
              <td>{book.price}</td>
              <td>{book.quantity}</td>
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => {
                    setBookToUpdate(book);
                    setUpdateBookModal(true);
                  }}
                >
                  Update
                </Button>
              </td>
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => deleteHandler(book)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default BooksTable;
