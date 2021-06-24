import React from "react";
import { Table } from "react-bootstrap";
import { fields } from "../../Utils";

function ConfigurationTable({ configurations, type }) {
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
          {configurations.map((configuration) => (
            <tr key={configuration.id}>
              <td>{configuration.dateCreated}</td>
              <td>
                {configuration.cpu.brand.toString() +
                  " " +
                  configuration.cpu.model.toString()}
              </td>
              <td>
                {configuration.gpu.brand.toString() +
                  " " +
                  configuration.gpu.model.toString()}
              </td>
              <td>
                {configuration.memory.brand.toString() +
                  " " +
                  configuration.memory.model.toString()}
              </td>
              <td>
                {configuration.motherboard.brand.toString() +
                  " " +
                  configuration.motherboard.model.toString()}
              </td>
              <td>
                {configuration.storage.brand.toString() +
                  " " +
                  configuration.storage.model.toString()}
              </td>
              <td>
                {configuration.psu.brand.toString() +
                  " " +
                  configuration.psu.model.toString()}
              </td>
              <td>
                {configuration.pcCase.brand.toString() +
                  " " +
                  configuration.pcCase.model.toString()}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default ConfigurationTable;
