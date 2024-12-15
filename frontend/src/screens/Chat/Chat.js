import React, { useState, useEffect } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import axios from "axios";
import "./Chat.css";

let stompClient = null;

const App = () => {
  const [messages, setMessages] = useState([]);
  const [userInput, setUserInput] = useState("");
  const [isMemoVisible, setIsMemoVisible] = useState(false);
  const [memoContent, setMemoContent] = useState("");
  const [userName, setUserName] = useState("익명");

  useEffect(() => {
    const fetchUserName = async () => {
      try {
        const response = await axios.get("/api/user");
      } catch (error) {
        console.error("Failed to fetch user name:", error);
        setUserName("익명");
      }
    };
    fetchUserName();
  }, []);

  const connect = () => {
    stompClient = new Client({
      brokerURL: "ws://localhost:8080/chat",
      onConnect: () => {
        console.log("Connected to WebSocket");
        stompClient.subscribe("/topic/messages", (message) => {
          const parsedMessage = JSON.parse(message.body);
          setMessages((prevMessages) => [...prevMessages, parsedMessage]);
        });
      },
      onStompError: (error) => {
        console.error("STOMP error:", error);
      },
    });

    stompClient.webSocketFactory = () =>
      new SockJS("http://localhost:8080/chat");

    stompClient.activate();
  };

  const sendMessage = () => {
    if (userInput.trim()) {
      const chatMessage = {
        sender: userName,
        content: userInput,
      };

      try {
        stompClient.publish({
          destination: "/app/chat",
          body: JSON.stringify(chatMessage),
        });
      } catch (error) {
        console.error("Error sending message:", error);
      }

      setMessages((prevMessages) => [...prevMessages, chatMessage]);
      setUserInput("");
    }
  };

  const toggleMemo = () => {
    setIsMemoVisible(!isMemoVisible);
  };

  const handleMemoChange = (e) => {
    setMemoContent(e.target.value);
  };

  return (
    <div className="App">
      <div className="header">
        <span className="book-title">『군주론』 - 마키아벨리</span>
        <div className="header-buttons">
          <button className="note-button" onClick={toggleMemo}>
            메모
          </button>
          <button className="exit-button">나가기</button>
        </div>
      </div>
      <div className="chat-container">
        <div className={`chat-section ${isMemoVisible ? "with-memo" : ""}`}>
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
            <button className="add-button">+</button>
            <input
              type="text"
              placeholder="여기에 내용을 입력해주세요."
              value={userInput}
              onChange={(e) => setUserInput(e.target.value)}
            />
            <button className="send-button" onClick={sendMessage}>
              📩
            </button>
          </div>
        </div>
        {isMemoVisible && (
          <div className="memo-section">
            <h3>메모</h3>
            <textarea
              className="memo-input"
              value={memoContent}
              onChange={handleMemoChange}
              placeholder="여기에 메모를 입력하세요."
            />
          </div>
        )}
      </div>
    </div>
  );
};

export default App;
