import React from "react";
import { Table, Button } from "react-bootstrap";
import { fields } from "../../Utils";

function GPUTable({ parts, type, setItem, setUpdateQuantityModal }) {
  return (
    <div>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            {fields[type].map((field) => (
              <th key={field}>{field}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {parts.map((part) => (
            <tr key={part.id}>
              <td>{part.brand}</td>
              <td>{part.model}</td>
              <td>{part.vram}</td>
              <td>{part.frequency}</td>
              <td>{part.price}</td>
              <td>{part.quantity}</td>
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => {
                    setItem(part);
                    setUpdateQuantityModal(true);
                  }}
                >
                  Update Stock
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default GPUTable;
