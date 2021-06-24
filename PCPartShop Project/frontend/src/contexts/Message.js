import React, { useState, createContext } from "react";

export const MessageContext = createContext();

export const MessageInfo = (props) => {
  const [request, setRequest] = useState({});

  return (
    <MessageContext.Provider value={[request, setRequest]}>
      {props.children}
    </MessageContext.Provider>
  );
};

export const MessageModalContext = createContext();

export const MessageModalInfo = (props) => {
  const [createConfigModal, setCreateConfigModal] = useState(false);

  return (
    <MessageModalContext.Provider
      value={[createConfigModal, setCreateConfigModal]}
    >
      {props.children}
    </MessageModalContext.Provider>
  );
};
