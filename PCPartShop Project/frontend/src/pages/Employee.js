import React, { useState, useEffect, useContext } from "react";
import NavbarEmployee from "../components/employee/NavbarEmployee";
import CPUTable from "../components/employee/tables/CPUTable";
import GPUTable from "../components/employee/tables/GPUTable";
import MemoryTable from "../components/employee/tables/MemoryTable";
import MotherboardTable from "../components/employee/tables/MotherboardTable";
import StorageTable from "../components/employee/tables/StorageTable";
import PcCaseTable from "../components/employee/tables/PcCaseTable";
import PSUTable from "../components/employee/tables/PSUTable";
import ConfigurationTable from "../components/employee/tables/ConfigurationTable";
import { PartsService } from "../api/services/Parts";
import { ConfigurationsService } from "../api/services/Configurations";
import { URLS } from "../api/Http";
import UpdateQuantity from "../components/employee/modals/UpdateQuantity";
import CreateConfiguration from "../components/employee/modals/CreateConfiguration";
import { MessageContext, MessageModalContext } from "../contexts/Message";
import { WebSocketConnection } from "../api/WebSocketConnection";

const Employee = () => {
  const [request, setRequest] = useContext(MessageContext);
  const [createConfigModal, setCreateConfigModal] =
    useContext(MessageModalContext);
  const [parts, setParts] = useState([]);
  const [configurations, setConfigurations] = useState([]);
  const [type, setType] = useState("CPU");
  const [updateQuantityModal, setUpdateQuantityModal] = useState(false);
  const [item, setItem] = useState({});

  const renderTable = () => {
    switch (type) {
      case "CPU":
        return (
          <CPUTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "GPU":
        return (
          <GPUTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "Memory":
        return (
          <MemoryTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "Motherboard":
        return (
          <MotherboardTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "Storage":
        return (
          <StorageTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "PSU":
        return (
          <PSUTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "PC Case":
        return (
          <PcCaseTable
            parts={parts}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      case "Configuration":
        return (
          <ConfigurationTable
            configurations={configurations}
            type={type}
            setItem={setItem}
            setUpdateQuantityModal={setUpdateQuantityModal}
          />
        );
      default:
        return "ERROR";
    }
  };

  useEffect(() => {
    WebSocketConnection.connect(true, setRequest, setCreateConfigModal);

    if (type === "Configuration") {
      ConfigurationsService.getAll()
        .then((value) => {
          setConfigurations(value);
        })
        .catch((error) => {
          setConfigurations([]);
          console.error("Could not fetch Configurations! " + error.message);
        });
    } else {
      PartsService.getAll(URLS[type])
        .then((value) => {
          setParts(value);
        })
        .catch((error) => {
          setParts([]);
          console.error("Could not fetch Parts! " + error.message);
        });
    }
  }, [type, setRequest, setCreateConfigModal]);

  return (
    <>
      <div>
        <NavbarEmployee type={type} setType={setType} setParts={setParts} />
        {renderTable()}
      </div>
      <UpdateQuantity
        updateQuantityModal={updateQuantityModal}
        setUpdateQuantityModal={setUpdateQuantityModal}
        parts={parts}
        setParts={setParts}
        type={type}
        item={item}
      />
      <CreateConfiguration
        createConfigModal={createConfigModal}
        setCreateConfigModal={setCreateConfigModal}
        request={request}
        setConfigurations={setConfigurations}
      />
    </>
  );
};

export default Employee;
