import React from "react";
import { Table, Button } from "react-bootstrap";
import { fields } from "../../Utils";
import { URLS } from "../../../api/Http";
import { PartsService } from "../../../api/services/Parts";

function CPUTable({
  parts,
  type,
  setParts,
  setUpdatePartModal,
  setPartToUpdate,
}) {
  const deleteHandler = async (part) => {
    await PartsService.deletePart(URLS[type], part.id).catch((error) => {
      console.error("Could not delete Part! " + error.message);
    });
    PartsService.getAll(URLS[type]).then((value) => {
      setParts(value);
    });
  };

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
              <td>{part.cores}</td>
              <td>{part.frequency}</td>
              <td>{part.integratedGraphics}</td>
              <td>{part.price}</td>
              <td>{part.quantity}</td>
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => {
                    setPartToUpdate(part);
                    setUpdatePartModal(true);
                  }}
                >
                  update
                </Button>
              </td>
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => {
                    deleteHandler(part);
                  }}
                >
                  delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default CPUTable;
