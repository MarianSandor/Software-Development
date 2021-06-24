import React, { useState, useContext } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import style from "../components.module.css";
import { AuthenticationContext } from "../../contexts/Authentication";
import { BooksService } from "../../api/services/Books";

export default function Modal({
  updateBookModal,
  setUpdateBookModal,
  setUpdateBooks,
  bookToUpdate,
}) {
  const [user, setUser] = useContext(AuthenticationContext);
  const [title, setTile] = useState("");
  const [author, setAuthor] = useState("");
  const [genre, setGenre] = useState("");
  const [quantity, setQuantity] = useState("");
  const [price, setPrice] = useState("");

  if (!updateBookModal) return null;

  const initializeFields = () => {
    setTile("");
    setAuthor("");
    setGenre("");
    setQuantity("");
    setPrice("");
  };

  const closeHandler = () => {
    initializeFields();

    setUpdateBookModal(false);
  };

  const updateHandler = (e) => {
    e.preventDefault();

    let data = {
      title: title === "" ? bookToUpdate.title : title,
      author: author === "" ? bookToUpdate.author : author,
      genre: genre === "" ? bookToUpdate.genre : genre,
      quantity: quantity === "" ? bookToUpdate.quantity : quantity,
      price: price === "" ? bookToUpdate.price : price,
    };

    BooksService.updateBook(user, bookToUpdate.id, data)
      .then((value) => {
        bookToUpdate = value;

        setUpdateBookModal(false);
        setUpdateBooks(true);
        initializeFields();
      })
      .catch((error) => {
        console.error("Failed to update book! " + error.message);
      });
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.bookModal}>
        <Form onSubmit={updateHandler}>
          <Form.Group controlId="formBasicTitle">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Title
            </Form.Label>
            <Form.Control
              type="text"
              placeholder={bookToUpdate.title}
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
              placeholder={bookToUpdate.author}
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
              placeholder={bookToUpdate.genre}
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
              placeholder={bookToUpdate.price}
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
              placeholder={bookToUpdate.quantity}
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
            Update
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
