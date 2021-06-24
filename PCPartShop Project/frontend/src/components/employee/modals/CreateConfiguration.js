import React, { useState } from "react";
import ReactDom from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Form } from "react-bootstrap";
import { Typeahead } from "react-bootstrap-typeahead";
import style from "../../components.module.css";
import { PartsService } from "../../../api/services/Parts";
import { ConfigurationsService } from "../../../api/services/Configurations";
import { URLS } from "../../../api/Http";

export default function Modal({
  createConfigModal,
  setCreateConfigModal,
  setConfigurations,
  request,
}) {
  const [cpus, setCpus] = useState([]);
  const [cpu, setCpu] = useState();
  const [gpus, setGpus] = useState([]);
  const [gpu, setGpu] = useState();
  const [memories, setMemories] = useState([]);
  const [memory, setMemory] = useState();
  const [motherboards, setMotherboards] = useState([]);
  const [motherboard, setMotherboard] = useState();
  const [psus, setPsus] = useState([]);
  const [psu, setPsu] = useState();
  const [storages, setStorages] = useState([]);
  const [storage, setStorage] = useState();
  const [pcCases, setPcCases] = useState([]);
  const [pcCase, setPcCase] = useState();
  const [description, setDescription] = useState("");

  if (!createConfigModal) return null;

  const setCpusHandler = () => {
    PartsService.getAll(URLS["CPU"])
      .then((value) => {
        setCpus(value);
      })
      .catch((error) => {
        setCpus([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setGpusHandler = () => {
    PartsService.getAll(URLS["GPU"])
      .then((value) => {
        setGpus(value);
      })
      .catch((error) => {
        setGpus([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setMemoriesHandler = () => {
    PartsService.getAll(URLS["Memory"])
      .then((value) => {
        setMemories(value);
      })
      .catch((error) => {
        setMemories([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setMotherboardsHandler = () => {
    PartsService.getAll(URLS["Motherboard"])
      .then((value) => {
        setMotherboards(value);
      })
      .catch((error) => {
        setMotherboards([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setPsusHandler = () => {
    PartsService.getAll(URLS["PSU"])
      .then((value) => {
        setPsus(value);
      })
      .catch((error) => {
        setPsus([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setStoragesHandler = () => {
    PartsService.getAll(URLS["Storage"])
      .then((value) => {
        setStorages(value);
      })
      .catch((error) => {
        setStorages([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const setPcCasesHandler = () => {
    PartsService.getAll(URLS["PC Case"])
      .then((value) => {
        setPcCases(value);
      })
      .catch((error) => {
        setPcCases([]);
        console.error("Could not fetch Parts! " + error.message);
      });
  };

  const closeHandler = () => {
    setCreateConfigModal(false);
  };

  const createHandler = async (e) => {
    e.preventDefault();

    let configuration = {
      userId: request.clientId,
      description: description,
      cpuId: cpu,
      gpuId: gpu,
      motherboardId: motherboard,
      memoryId: memory,
      storageId: storage,
      psuId: psu,
      pcCaseId: pcCase,
    };

    await ConfigurationsService.addConfiguration(configuration).catch(
      (error) => {
        console.error("Failed to create configuration! " + error.message);
      }
    );

    ConfigurationsService.getAll()
      .then((value) => {
        setConfigurations(value);
      })
      .catch((error) => {
        setConfigurations([]);
        console.error("Could not fetch Configurations! " + error.message);
      });

    setCreateConfigModal(false);
  };

  return ReactDom.createPortal(
    <>
      <div className={style.overlay} />
      <div className={style.createConfig}>
        <Form onSubmit={createHandler}>
          <Form.Label
            className="text-center"
            style={{ width: "100%", fontWeight: "bold", marginBottom: "2rem" }}
          >
            {request.content}
          </Form.Label>
          <Form.Group>
            <Typeahead
              id="1"
              labelKey="name"
              onFocus={setCpusHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setCpu(parseInt(index));
              }}
              options={cpus.map((cpu) => {
                return (
                  "(" +
                  cpu.id +
                  ")" +
                  " " +
                  cpu.brand +
                  " " +
                  cpu.model +
                  " " +
                  cpu.frequency +
                  "GHz"
                );
              })}
              placeholder="Choose a CPU..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="2"
              labelKey="name"
              onFocus={setGpusHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setGpu(parseInt(index));
              }}
              options={gpus.map((gpu) => {
                return (
                  "(" +
                  gpu.id +
                  ")" +
                  " " +
                  gpu.brand +
                  " " +
                  gpu.model +
                  " " +
                  gpu.vram +
                  "GB"
                );
              })}
              placeholder="Choose a GPU..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="3"
              labelKey="name"
              onFocus={setMotherboardsHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setMotherboard(parseInt(index));
              }}
              options={motherboards.map((motherboard) => {
                return (
                  "(" +
                  motherboard.id +
                  ")" +
                  " " +
                  motherboard.brand +
                  " " +
                  motherboard.model
                );
              })}
              placeholder="Choose a Motherboard..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="4"
              labelKey="name"
              onFocus={setMemoriesHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setMemory(parseInt(index));
              }}
              options={memories.map((memory) => {
                return (
                  "(" +
                  memory.id +
                  ")" +
                  " " +
                  memory.brand +
                  " " +
                  memory.model +
                  " " +
                  memory.frequency +
                  "Mhz"
                );
              })}
              placeholder="Choose a Memory..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="5"
              labelKey="name"
              onFocus={setStoragesHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setStorage(parseInt(index));
              }}
              options={storages.map((storage) => {
                return (
                  "(" +
                  storage.id +
                  ")" +
                  " " +
                  storage.brand +
                  " " +
                  storage.model +
                  " " +
                  storage.capacity +
                  "GB " +
                  storage.type
                );
              })}
              placeholder="Choose a Storage..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="6"
              labelKey="name"
              onFocus={setPsusHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setPsu(parseInt(index));
              }}
              options={psus.map((psu) => {
                return (
                  "(" +
                  psu.id +
                  ")" +
                  " " +
                  psu.brand +
                  " " +
                  psu.model +
                  " " +
                  psu.efficiency +
                  " " +
                  psu.wattage +
                  "W"
                );
              })}
              placeholder="Choose a Power Supply..."
            />
          </Form.Group>
          <Form.Group>
            <Typeahead
              id="6"
              labelKey="name"
              onFocus={setPcCasesHandler}
              onChange={(sel) => {
                let index = sel[0].split(" ")[0];
                index = index.substring(
                  index.indexOf("(") + 1,
                  index.indexOf(")")
                );
                if (sel.length > 0) setPcCase(parseInt(index));
              }}
              options={pcCases.map((pcCase) => {
                return (
                  "(" +
                  pcCase.id +
                  ")" +
                  " " +
                  pcCase.brand +
                  " " +
                  pcCase.model
                );
              })}
              placeholder="Choose a PC Case..."
            />
          </Form.Group>
          <Form.Group controlId="exampleForm.ControlTextarea1">
            <Form.Label className="text-center" style={{ width: "100%" }}>
              Description
            </Form.Label>
            <Form.Control
              as="textarea"
              rows="3"
              onChange={(e) => setDescription(e.target.value)}
            />
          </Form.Group>
          <Button
            variant="primary"
            type="submit"
            className="text-center"
            style={{ width: "100%" }}
            onClick={createHandler}
          >
            Create
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
