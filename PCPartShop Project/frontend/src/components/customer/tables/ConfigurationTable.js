import React from "react";
import { Table, Button } from "react-bootstrap";
import { fields } from "../../Utils";
import { ConfigurationsService } from "../../../api/services/Configurations";

function ConfigurationTable({ configurations, type }) {
  const downloadPDF = (data) => {
    const blob = new Blob([data], { type: "application/pdf" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.setAttribute("hidden", "");
    a.setAttribute("href", url);
    a.setAttribute("download", "configuration.pdf");
    document.body.appendChild(a);
    a.click();
  };

  const exportHandler = (id) => {
    ConfigurationsService.report(id)
      .then((value) => {
        downloadPDF(value);
      })
      .catch((error) => {
        console.error("Could not export report! " + error.message);
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
              <td>
                <Button
                  variant="primary"
                  type="button"
                  onClick={() => {
                    exportHandler(configuration.id);
                  }}
                >
                  Export PDF
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default ConfigurationTable;
