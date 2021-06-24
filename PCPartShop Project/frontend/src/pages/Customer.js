import React, { useState, useEffect } from "react";
import NavbarCustomer from "../components/customer/NavbarCustomer";
import CPUTable from "../components/customer/tables/CPUTable";
import GPUTable from "../components/customer/tables/GPUTable";
import MemoryTable from "../components/customer/tables/MemoryTable";
import MotherboardTable from "../components/customer/tables/MotherboardTable";
import StorageTable from "../components/customer/tables/StorageTable";
import PcCaseTable from "../components/customer/tables/PcCaseTable";
import PSUTable from "../components/customer/tables/PSUTable";
import ConfigurationTable from "../components/customer/tables/ConfigurationTable";
import { PartsService } from "../api/services/Parts";
import { UsersService } from "../api/services/Users";
import { URLS } from "../api/Http";
import { WebSocketConnection } from "../api/WebSocketConnection";

const Customer = () => {
  const [parts, setParts] = useState([]);
  const [configurations, setConfigurations] = useState([]);
  const [type, setType] = useState("CPU");

  const renderTable = () => {
    switch (type) {
      case "CPU":
        return <CPUTable parts={parts} type={type} setParts={setParts} />;
      case "GPU":
        return <GPUTable parts={parts} type={type} setParts={setParts} />;
      case "Memory":
        return <MemoryTable parts={parts} type={type} setParts={setParts} />;
      case "Motherboard":
        return (
          <MotherboardTable parts={parts} type={type} setParts={setParts} />
        );
      case "Storage":
        return <StorageTable parts={parts} type={type} setParts={setParts} />;
      case "PSU":
        return <PSUTable parts={parts} type={type} setParts={setParts} />;
      case "PC Case":
        return <PcCaseTable parts={parts} type={type} setParts={setParts} />;
      case "Configuration":
        return (
          <ConfigurationTable configurations={configurations} type={type} />
        );
      default:
        return "ERROR";
    }
  };

  useEffect(() => {
    WebSocketConnection.connect(false);
    let user = JSON.parse(localStorage.getItem("user"));

    if (type === "Configuration") {
      UsersService.getUserConfigurations(user.id)
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
  }, [type]);

  return (
    <div>
      <NavbarCustomer
        type={type}
        setType={setType}
        setParts={setParts}
        setConfigurations={setConfigurations}
      />
      {renderTable()}
    </div>
  );
};

export default Customer;
