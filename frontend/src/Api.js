import axios from "axios";

const BASE_URL = "http://localhost:8080";

// Chat 관련 API
export const sendMessageAPI = async (message) => {
  try {
    await axios.post(`${BASE_URL}/app/sendMessage`, message, {
      headers: { "Content-Type": "application/json" },
    });
  } catch (error) {
    console.error("Error sending message:", error);
    throw error;
  }
};

export const generateTopicsAPI = async () => {
  try {
    await axios.post(
      `${BASE_URL}/chat/generate-topics`,
      ["response1", "response2"],
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    alert("발제문 생성 완료.");
  } catch (error) {
    console.error("Error generating topics:", error);
    throw error;
  }
};

export const collectResponsesAPI = async (topic) => {
  try {
    const response = await axios.post(
      `${BASE_URL}/chat/collect-responses`,
      null,
      {
        params: { topic },
      }
    );
    return response.data;
  } catch (error) {
    console.error("Error collecting responses:", error);
    throw error;
  }
};

export const freeDiscussionAPI = async () => {
  try {
    await axios.post(`${BASE_URL}/chat/free-discussion`);
    alert("자유토론 시작");
  } catch (error) {
    console.error("Error starting free discussion:", error);
    throw error;
  }
};

export const summarizeMeetingAPI = async () => {
  try {
    await axios.post(`${BASE_URL}/chat/summarize-meeting`);
    alert("요약 완료");
  } catch (error) {
    console.error("Error summarizing meeting:", error);
    throw error;
  }
};

// FeedbackForm 관련 API
export const submitFeedbackAPI = async (feedback) => {
  try {
    await axios.post(
      `${BASE_URL}/feedback`,
      { content: feedback },
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    alert("소감문이 성공적으로 제출되었습니다.");
  } catch (error) {
    console.error("Error submitting feedback:", error);
    throw error;
  }
};
