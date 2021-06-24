import React, { useState, useContext } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import style from "../components.module.css";
import { AuthenticationContext } from "../../contexts/Authentication";
import { BooksService } from "../../api/services/Books";

export default function Modal({
  addBookModal,
  setAddBookModal,
  setUpdateBooks,
}) {
  const [user, setUser] = useContext(AuthenticationContext);
  const [title, setTile] = useState("");
  const [author, setAuthor] = useState("");
  const [genre, setGenre] = useState("");
  const [quantity, setQuantity] = useState("");
  const [price, setPrice] = useState("");

  if (!addBookModal) return null;

  const initializeFields = () => {
    setTile("");
    setAuthor("");
    setGenre("");
    setQuantity("");
    setPrice("");
  };

  const closeHandler = () => {
    initializeFields();

    setAddBookModal(false);
  };

  const addHandler = (e) => {
    e.preventDefault();

    let data = {
      title: title,
      author: author,
      genre: genre,
      quantity: parseInt(quantity),
      price: parseFloat(price),
    };

    BooksService.addBook(user, data).catch((error) => {
      console.error("Failed to add book! " + error.message);
    });

    setAddBookModal(false);
    setUpdateBooks(true);
    initializeFields();
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.bookModal}>
        <Form onSubmit={addHandler}>
          <Form.Group controlId="formBasicTitle">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Title
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="title"
              value={title}
              onChange={(e) => setTile(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="formBasicAuthor">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Author
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="author"
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="formBasicGenre">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Genre
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="genre"
              value={genre}
              onChange={(e) => setGenre(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="formBasicPrice">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Price
            </Form.Label>
            <Form.Control
              type="number"
              step="0.01"
              placeholder="price"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="formBasicQuantity">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Quantity
            </Form.Label>
            <Form.Control
              type="number"
              placeholder="quantity"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
            />
          </Form.Group>

          <Button
            variant="primary"
            type="submit"
            className="text-center"
            style={{ width: "100%" }}
          >
            Add
          </Button>
        </Form>
        <button className={style.closeButton} onClick={closeHandler}>
          X
        </button>
      </div>
    </>,
    document.getElementById("portal")
  );
}
