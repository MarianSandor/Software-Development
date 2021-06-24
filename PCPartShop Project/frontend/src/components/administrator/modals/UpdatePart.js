import React, { useState } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import style from "../../components.module.css";
import { PartsService } from "../../../api/services/Parts";
import { URLS } from "../../../api/Http";

export default function Modal({
  updatePartModal,
  setUpdatePartModal,
  parts,
  setParts,
  partType,
  partToUpdate,
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

  if (!updatePartModal) return null;

  const initializeFields = () => {
    setBrand("");
    setModel("");
    setQuantity(0);
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
    setWattage(0);
    setCapacity(0);
  };

  const closeHandler = () => {
    initializeFields();

    setUpdatePartModal(false);
  };

  const updateHandler = async (e) => {
    e.preventDefault();

    let data = {
      brand: brand === "" ? partToUpdate.brand : brand,
      model: model === "" ? partToUpdate.model : model,
      quantity: quantity === 0 ? partToUpdate.quantity : Math.round(quantity),
      price: price === 0 ? partToUpdate.price : price,
      cores: cores === 0 ? partToUpdate.cores : Math.round(cores),
      frequency: frequency === 0 ? partToUpdate.frequency : frequency,
      integratedGraphics:
        integratedGraphics === ""
          ? partToUpdate.integratedGraphics
          : integratedGraphics,
      vram: vram === 0 ? partToUpdate.vram : Math.round(vram),
      size: size === 0 ? partToUpdate.size : Math.round(size),
      type: type === "" ? partToUpdate.type : type,
      socket: socket === "" ? partToUpdate.socket : socket,
      formFactor: formFactor === "" ? partToUpdate.formFactor : formFactor,
      efficiency: efficiency === "" ? partToUpdate.efficiency : efficiency,
      wattage: wattage === 0 ? partToUpdate.wattage : Math.round(wattage),
      capacity: capacity === 0 ? partToUpdate.capacity : Math.round(capacity),
    };

    await PartsService.updatePart(URLS[partType], partToUpdate.id, data)
      .then((value) => {
        const newList = parts.map((part) => {
          if (part.id === partToUpdate.id) {
            return value;
          }
          return part;
        });
        setParts(newList);
      })
      .catch((error) => {
        console.error("Failed to update part! " + error.message);
      });

    setUpdatePartModal(false);
    initializeFields();
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.partModal}>
        <Form onSubmit={updateHandler}>
          <Form.Group controlId="1">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Brand
            </Form.Label>
            <Form.Control
              type="text"
              placeholder={partToUpdate.brand}
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
              placeholder={partToUpdate.model}
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
                value={capacity === 0 ? partToUpdate.capacity : capacity}
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
                placeholder={partToUpdate.type}
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
                placeholder={partToUpdate.formFactor}
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
                placeholder={partToUpdate.socket}
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
                value={size === 0 ? partToUpdate.size : size}
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
                value={cores === 0 ? partToUpdate.cores : cores}
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
                value={vram === 0 ? partToUpdate.vram : vram}
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
                value={frequency === 0 ? partToUpdate.frequency : frequency}
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
                placeholder={partToUpdate.integratedGraphics}
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
                value={wattage === 0 ? partToUpdate.wattage : wattage}
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
                placeholder={partToUpdate.efficiency}
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
              value={quantity === 0 ? partToUpdate.quantity : quantity}
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
              value={price === 0 ? partToUpdate.price : price}
              onChange={(e) => setPrice(e.target.value)}
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
