import React from "react";
import { Table, Button } from "react-bootstrap";
import { fieldsAdmin } from "../../Utils";
import { UsersService } from "../../../api/services/Users";

function UsersTable({
  users,
  setUsers,
  type,
  setUserToUpdate,
  setUpdateUserModal,
}) {
  const deleteHandler = async (user) => {
    await UsersService.deleteUser(user.id).catch((error) => {
      console.error("Could not delete User! " + error.message);
    });
    UsersService.getAll().then((value) => {
      setUsers(value);
    });
  };

  return (
    <div>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            {fieldsAdmin[type].map((field) => (
              <th key={field}>{field}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>{user.role}</td>
              <td>
                {user.role !== "ADMIN" && (
                  <Button
                    variant="primary"
                    type="button"
                    onClick={() => {
                      setUserToUpdate(user);
                      setUpdateUserModal(true);
                    }}
                  >
                    Update
                  </Button>
                )}
              </td>
              <td>
                {user.role !== "ADMIN" && (
                  <Button
                    variant="primary"
                    type="button"
                    onClick={() => deleteHandler(user)}
                  >
                    Delete
                  </Button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default UsersTable;
