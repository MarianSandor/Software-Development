import React, { useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Table, Button } from "react-bootstrap";
import { BooksService } from "../../api/services/Books";
import { AuthenticationContext } from "../../contexts/Authentication";

const BooksTable = ({ books, setBooks, setUpdateBooks }) => {
  const [user, setUser] = useContext(AuthenticationContext);

  const sellHandler = (book) => {
    BooksService.updateQuantity(user, book.id, 1)
      .then((value) => {
        book = value;
        setUpdateBooks(true);
      })
      .catch((error) => {
        console.error("Could not sell book! " + error.message);
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
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.genre}</td>
              <td>{book.price}</td>
              <td>
                {book.quantity > 0 ? (
                  <Button
                    variant="primary"
                    type="button"
                    onClick={() => sellHandler(book)}
                  >
                    Sell (stock: {book.quantity})
                  </Button>
                ) : (
                  "Out of stock!"
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default BooksTable;
