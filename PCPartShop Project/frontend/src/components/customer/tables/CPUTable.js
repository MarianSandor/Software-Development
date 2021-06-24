import React from "react";
import { Table, Button } from "react-bootstrap";
import { fields } from "../../Utils";
import { URLS } from "../../../api/Http";
import { PartsService } from "../../../api/services/Parts";

function CPUTable({ parts, type, setParts }) {
  let user = JSON.parse(localStorage.getItem("user"));

  const orderHandler = (e, item) => {
    e.preventDefault();

    const data = {
      email: user.email,
      quantity: 1,
      product: item,
    };

    PartsService.order(URLS[type], item.id, data)
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
        console.error("Could not Order Part! " + error.message);
      });
  };

  return (
    <div>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            {fields[type]
              .filter((field) => field !== "Quantity")
              .map((field) => (
                <th key={field}>{field}</th>
              ))}
          </tr>
        </thead>
        <tbody>
          {parts.map((part) => (
            <tr key={part.id}>
              <td>{part.brand}</td>
              <td>{part.model}</td>
              <td>{part.cores}</td>
              <td>{part.frequency}</td>
              <td>{part.integratedGraphics}</td>
              <td>{part.price}</td>
              <td>
                {part.quantity > 0 ? (
                  <Button
                    variant="primary"
                    type="button"
                    onClick={(e) => {
                      orderHandler(e, part);
                    }}
                  >
                    Order
                  </Button>
                ) : (
                  "Out of stock"
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default CPUTable;
