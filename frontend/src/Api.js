import axios from "axios";

// 백엔드 API의 기본 URL
const BASE_URL = "http://localhost:8080";
const CHAT_API_URL = `${BASE_URL}/chat`;

// Chat 관련 API
/**
 * 사용자가 채팅 메시지를 전송합니다.
 * @param {Object} message - 메시지 객체
 */
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

/**
 * 사용자 응답을 기반으로 주제를 생성합니다.
 * @param {Array} responses - 사용자 응답 배열
 * @returns {Promise} 생성된 주제 배열
 */
export const generateTopicsAPI = async (responses) => {
  try {
    const response = await axios.post(`${CHAT_API_URL}/generate-topics`, responses, {
      headers: { "Content-Type": "application/json" },
    });
    alert("발제문 생성 완료.");
    return response.data;
  } catch (error) {
    console.error("Error generating topics:", error);
    throw error;
  }
};

/**
 * 특정 주제에 대한 사용자 응답을 수집합니다.
 * @param {String} topic - 주제 이름
 * @returns {Promise} 수집된 응답 배열
 */
export const collectResponsesAPI = async (topic) => {
  try {
    const response = await axios.post(`${CHAT_API_URL}/collect-responses`, null, {
      params: { topic },
    });
    return response.data;
  } catch (error) {
    console.error("Error collecting responses:", error);
    throw error;
  }
};

/**
 * 자유 토론을 시작합니다.
 */
export const freeDiscussionAPI = async () => {
  try {
    await axios.post(`${CHAT_API_URL}/free-discussion`);
    alert("자유토론 시작");
  } catch (error) {
    console.error("Error starting free discussion:", error);
    throw error;
  }
};

/**
 * 토론 회의를 요약합니다.
 * @param {Array} topics - 토론 주제 배열
 * @param {Array} allResponses - 각 주제별 응답 배열
 */
export const summarizeMeetingAPI = async (topics, allResponses) => {
  try {
    await axios.post(
      `${CHAT_API_URL}/summarize-meeting`,
      { topics, allResponses },
      { headers: { "Content-Type": "application/json" } }
    );
    alert("요약 완료");
  } catch (error) {
    console.error("Error summarizing meeting:", error);
    throw error;
  }
};

// FeedbackForm 관련 API
/**
 * 소감문을 제출합니다.
 * @param {String} feedback - 사용자 소감문
 */
export const submitFeedbackAPI = async (feedback) => {
  try {
    await axios.post(
      `${BASE_URL}/feedback`,
      { content: feedback },
      { headers: { "Content-Type": "application/json" } }
    );
    alert("소감문이 성공적으로 제출되었습니다.");
  } catch (error) {
    console.error("Error submitting feedback:", error);
    throw error;
  }
};
