import React, { useState } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import style from "../../components.module.css";
import { PartsService } from "../../../api/services/Parts";
import { URLS } from "../../../api/Http";

export default function Modal({
  updateQuantityModal,
  setUpdateQuantityModal,
  parts,
  setParts,
  type,
  item,
}) {
  const [quantity, setQuantity] = useState(-1);

  if (!updateQuantityModal) return null;

  const closeHandler = () => {
    setQuantity(-1);

    setUpdateQuantityModal(false);
  };

  const updateHandler = (e) => {
    e.preventDefault();

    PartsService.updateQuantity(URLS[type], item.id, quantity)
      .then((value) => {
        const newList = parts.map((part) => {
          if (part.id === item.id) {
            return value;
          }
          return part;
        });

        setParts(newList);
      })
      .catch((error) => {
        console.error("Failed to update part! " + error.message);
      });

    setQuantity(-1);
    setUpdateQuantityModal(false);
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.updateQuantity}>
        <Form onSubmit={updateHandler}>
          <Form.Group controlId="formBasicUsername">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              quantity
            </Form.Label>
            <Form.Control
              type="number"
              step="1"
              value={quantity === -1 ? item.quantity : quantity}
              onChange={(e) => setQuantity(e.target.value)}
            />
          </Form.Group>

          <Button
            variant="primary"
            type="submit"
            className="text-center"
            style={{ width: "100%" }}
            onClick={updateHandler}
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
