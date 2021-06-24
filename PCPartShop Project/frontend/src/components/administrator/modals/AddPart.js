import React, { useState } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import style from "../../components.module.css";
import { PartsService } from "../../../api/services/Parts";
import { URLS } from "../../../api/Http";

export default function Modal({
  addPartModal,
  setAddPartModal,
  parts,
  setParts,
  partType,
}) {
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [quantity, setQuantity] = useState(0);
  const [price, setPrice] = useState(0.0);
  const [cores, setCores] = useState(0);
  const [frequency, setFrequency] = useState(0.0);
  const [integratedGraphics, setIntegratedGraphics] = useState("");
  const [vram, setVram] = useState(0);
  const [size, setSize] = useState(0);
  const [type, setType] = useState("");
  const [socket, setSocket] = useState("");
  const [formFactor, setFormFactor] = useState("");
  const [efficiency, setEfficiency] = useState("");
  const [wattage, setWattage] = useState(0);
  const [capacity, setCapacity] = useState(0);

  if (!addPartModal) return null;

  const initializeFields = () => {
    setBrand("");
    setModel("");
    setQuantity("");
    setPrice(0.0);
    setCores(0);
    setFrequency(0.0);
    setIntegratedGraphics("");
    setVram(0);
    setSize(0);
    setType("");
    setSocket("");
    setFormFactor("");
    setEfficiency("");
    setWattage("");
    setCapacity(0);
  };

  const closeHandler = () => {
    initializeFields();

    setAddPartModal(false);
  };

  const addHandler = async (e) => {
    e.preventDefault();

    let data = {
      brand: brand,
      model: model,
      quantity: Math.round(quantity),
      price: price,
      cores: Math.round(cores),
      frequency: frequency,
      integratedGraphics: integratedGraphics,
      vram: Math.round(vram),
      size: Math.round(size),
      type: type,
      socket: socket,
      formFactor: formFactor,
      efficiency: efficiency,
      wattage: Math.round(wattage),
      capacity: Math.round(capacity),
    };

    await PartsService.addPart(URLS[partType], data)
      .then((value) => {
        let newParts = parts;
        newParts.push(value);
        setParts(newParts);
      })
      .catch((error) => {
        console.error("Failed to add part! " + error.message);
      });

    setAddPartModal(false);
    initializeFields();
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.partModal}>
        <Form onSubmit={addHandler}>
          <Form.Group controlId="1">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Brand
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="brand"
              value={brand}
              onChange={(e) => setBrand(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="2">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Model
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="model"
              value={model}
              onChange={(e) => setModel(e.target.value)}
            />
          </Form.Group>

          {["Storage"].includes(partType) && (
            <Form.Group controlId="10">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Capacity
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="capacity"
                value={capacity}
                onChange={(e) => setCapacity(e.target.value)}
              />
            </Form.Group>
          )}

          {["Memory", "Storage"].includes(partType) && (
            <Form.Group controlId="5">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Type
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="type"
                value={type}
                onChange={(e) => setType(e.target.value)}
              />
            </Form.Group>
          )}

          {["Motherboard", "PSU", "PC Case"].includes(partType) && (
            <Form.Group controlId="6">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Form Factor
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="formFactor"
                value={formFactor}
                onChange={(e) => setFormFactor(e.target.value)}
              />
            </Form.Group>
          )}

          {["Motherboard"].includes(partType) && (
            <Form.Group controlId="7">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Socket
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="socket"
                value={socket}
                onChange={(e) => setSocket(e.target.value)}
              />
            </Form.Group>
          )}

          {["Memory"].includes(partType) && (
            <Form.Group controlId="8">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Size
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="size"
                value={size}
                onChange={(e) => setSize(e.target.value)}
              />
            </Form.Group>
          )}

          {["CPU"].includes(partType) && (
            <Form.Group controlId="10">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Number Of Cores
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="cores"
                value={cores}
                onChange={(e) => setCores(e.target.value)}
              />
            </Form.Group>
          )}

          {["GPU"].includes(partType) && (
            <Form.Group controlId="10">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                VRam Memory
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="VRam"
                value={vram}
                onChange={(e) => setVram(e.target.value)}
              />
            </Form.Group>
          )}

          {["Memory", "CPU", "GPU"].includes(partType) && (
            <Form.Group controlId="9">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Frequency
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="frequency"
                value={frequency}
                onChange={(e) => setFrequency(e.target.value)}
              />
            </Form.Group>
          )}

          {["CPU"].includes(partType) && (
            <Form.Group controlId="11">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Integrated Graphics
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="integrated graphics"
                value={integratedGraphics}
                onChange={(e) => setIntegratedGraphics(e.target.value)}
              />
            </Form.Group>
          )}

          {["PSU"].includes(partType) && (
            <Form.Group controlId="9">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Wattage
              </Form.Label>
              <Form.Control
                type="number"
                step="1"
                placeholder="wattage"
                value={wattage}
                onChange={(e) => setWattage(e.target.value)}
              />
            </Form.Group>
          )}

          {["PSU"].includes(partType) && (
            <Form.Group controlId="11">
              <Form.Label className="text-center" style={{ width: "100%" }}>
                Efficiency
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="efficiency"
                value={efficiency}
                onChange={(e) => setEfficiency(e.target.value)}
              />
            </Form.Group>
          )}

          <Form.Group controlId="3">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Quantity
            </Form.Label>
            <Form.Control
              type="number"
              step="1"
              placeholder="quantity"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
            />
          </Form.Group>

          <Form.Group controlId="4">
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
