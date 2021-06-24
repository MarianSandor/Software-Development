import React, { useState, useEffect } from "react";
import NavbarAdministrator from "../components/administrator/NavbarAdministrator";
import CPUTable from "../components/administrator/tables/CPUTable";
import GPUTable from "../components/administrator/tables/GPUTable";
import MemoryTable from "../components/administrator/tables/MemoryTable";
import MotherboardTable from "../components/administrator/tables/MotherboardTable";
import StorageTable from "../components/administrator/tables/StorageTable";
import PcCaseTable from "../components/administrator/tables/PcCaseTable";
import PSUTable from "../components/administrator/tables/PSUTable";
import ConfigurationTable from "../components/administrator/tables/ConfigurationTable";
import UsersTable from "../components/administrator/tables/UsersTable";
import { PartsService } from "../api/services/Parts";
import { UsersService } from "../api/services/Users";
import { URLS } from "../api/Http";
import { ConfigurationsService } from "../api/services/Configurations";
import AddUser from "../components/administrator/modals/AddUser";
import UpdateUser from "../components/administrator/modals/UpdateUser";
import AddPart from "../components/administrator/modals/AddPart";
import UpdatePart from "../components/administrator/modals/UpdatePart";

const Administrator = () => {
  const [parts, setParts] = useState([]);
  const [configurations, setConfigurations] = useState([]);
  const [users, setUsers] = useState([]);
  const [type, setType] = useState("Users");
  const [addUserModal, setAddUserModal] = useState(false);
  const [updateUserModal, setUpdateUserModal] = useState(false);
  const [userToUpdate, setUserToUpdate] = useState({});
  const [addPartModal, setAddPartModal] = useState(false);
  const [updatePartModal, setUpdatePartModal] = useState(false);
  const [partToUpdate, setPartToUpdate] = useState({});

  const renderTable = () => {
    switch (type) {
      case "Users":
        return (
          <UsersTable
            users={users}
            setUsers={setUsers}
            type={type}
            setUserToUpdate={setUserToUpdate}
            setAddUserModal={setAddUserModal}
            setUpdateUserModal={setUpdateUserModal}
          />
        );
      case "CPU":
        return (
          <CPUTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "GPU":
        return (
          <GPUTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "Memory":
        return (
          <MemoryTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "Motherboard":
        return (
          <MotherboardTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "Storage":
        return (
          <StorageTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "PSU":
        return (
          <PSUTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "PC Case":
        return (
          <PcCaseTable
            parts={parts}
            type={type}
            setParts={setParts}
            setUpdatePartModal={setUpdatePartModal}
            setPartToUpdate={setPartToUpdate}
          />
        );
      case "Configuration":
        return (
          <ConfigurationTable
            configurations={configurations}
            setConfigurations={setConfigurations}
            type={type}
          />
        );
      default:
        return "ERROR";
    }
  };

  useEffect(() => {
    if (type === "Users") {
      UsersService.getAll()
        .then((value) => {
          setUsers(value);
        })
        .catch((error) => {
          setUsers([]);
          console.error("Could not fetch Users! " + error.message);
        });
    } else if (type === "Configuration") {
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
  }, [type]);

  return (
    <>
      <div>
        <NavbarAdministrator
          type={type}
          setType={setType}
          setParts={setParts}
          setConfigurations={setConfigurations}
          setAddUserModal={setAddUserModal}
          setAddPartModal={setAddPartModal}
        />
        {renderTable()}
      </div>
      <AddUser
        addUserModal={addUserModal}
        setAddUserModal={setAddUserModal}
        setUsers={setUsers}
        users={users}
      />
      <UpdateUser
        updateUserModal={updateUserModal}
        setUpdateUserModal={setUpdateUserModal}
        users={users}
        setUsers={setUsers}
        userToUpdate={userToUpdate}
      />
      <AddPart
        addPartModal={addPartModal}
        setAddPartModal={setAddPartModal}
        setParts={setParts}
        parts={parts}
        partType={type}
      />
      <UpdatePart
        updatePartModal={updatePartModal}
        setUpdatePartModal={setUpdatePartModal}
        setParts={setParts}
        parts={parts}
        partType={type}
        partToUpdate={partToUpdate}
      />
    </>
  );
};

export default Administrator;
