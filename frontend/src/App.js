import React, { useState, useEffect } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import "./App.css";

let stompClient = null;

const App = () => {
  const [messages, setMessages] = useState([]);
  const [userInput, setUserInput] = useState("");
  const [isConnected, setIsConnected] = useState(false);

  const userName = "익명"; // 기본 사용자 이름 설정

  const connect = () => {
    console.log("Connecting...");
    stompClient = new Client({
      brokerURL: "ws://localhost:8080/chat",
      onConnect: () => {
        console.log("Connected to WebSocket server!");
        setIsConnected(true);

        stompClient.subscribe("/topic/messages", (message) => {
          const parsedMessage = JSON.parse(message.body);
          setMessages((prevMessages) => [...prevMessages, parsedMessage]);
        });
      },
      onStompError: (error) => {
        console.error("STOMP error:", error);
      },
    });

    // WebSocket 지원이 없는 경우 SockJS 사용
    stompClient.webSocketFactory = () =>
      new SockJS("http://localhost:8080/chat");

    stompClient.activate();
  };

  const sendMessage = () => {
    if (userInput.trim() && stompClient) {
      const chatMessage = {
        sender: userName,
        content: userInput,
      };
      stompClient.publish({
        destination: "/app/sendMessage",
        body: JSON.stringify(chatMessage),
      });
      setUserInput("");
    }
  };

  useEffect(() => {
    connect(); // 컴포넌트가 로드될 때 자동으로 연결
    return () => {
      if (stompClient) {
        stompClient.deactivate();
      }
    };
  }, []);

  return (
    <div className="App">
      <div className="chat-container">
        <h1>실시간 채팅</h1>
        {isConnected ? (
          <>
            <div className="chat-messages">
              {messages.map((msg, index) => (
                <div
                  key={index}
                  className={`message ${msg.sender === userName ? "own" : ""}`}
                >
                  <strong>{msg.sender}: </strong>
                  {msg.content}
                </div>
              ))}
            </div>
            <div className="chat-input">
              <input
                type="text"
                placeholder="메시지를 입력하세요"
                value={userInput}
                onChange={(e) => setUserInput(e.target.value)}
              />
              <button onClick={sendMessage}>Send</button>
            </div>
          </>
        ) : (
          <p>채팅 서버에 연결중...</p>
        )}
      </div>
    </div>
  );
};

export default App;
