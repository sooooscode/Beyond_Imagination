import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Chat from "./screens/Chat/Chat";
import FeedbackForm from "./screens/FeedbackForm/FeedbackForm";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<FeedbackForm />} />
        <Route path="/chat" element={<Chat />} />
        <Route path="/feedback" element={<FeedbackForm />} />
      </Routes>
    </Router>
  );
};

export default App;
