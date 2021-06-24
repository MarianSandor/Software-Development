import * as SockJS from "sockjs-client";
import Stomp from "stompjs";
import authHeader from "./Http";
var stompClient = null;

const connect = async (subscribe, setRequest, setCreateConfigModal) => {
  var socket = new SockJS("http://localhost:8088/gs-guide-websocket");
  stompClient = Stomp.over(socket);
  stompClient.connect(authHeader(), function (frame) {
    console.log("Connected: " + frame);
    if (subscribe) {
      stompClient.subscribe("/topic/alerts", function (alert) {
        let message = JSON.parse(alert.body);
        setCreateConfigModal(true);
        setRequest(message);
      });
    }
  });
};

const disconnect = async () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }

  console.log("Disconnected");
};

const sendMessage = async (data) => {
  stompClient.send("/app/message", {}, JSON.stringify(data));
};

export const WebSocketConnection = {
  stompClient,
  connect,
  disconnect,
  sendMessage,
};
