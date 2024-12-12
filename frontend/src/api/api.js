import axios from "axios";

// 백엔드 API의 기본 URL
const API_BASE_URL = "http://localhost:8080/chat";

/**
 * 사용자 응답을 기반으로 주제를 생성합니다.
 * @param {Array} responses - 사용자 응답 배열
 * @returns {Promise} 생성된 주제 배열
 */
export const generateTopics = async (responses) => {
  const response = await axios.post(`${API_BASE_URL}/generate-topics`, responses, {
    headers: { "Content-Type": "application/json" },
  });
  return response.data;
};

/**
 * 특정 주제에 대한 사용자 응답을 수집합니다.
 * @param {String} topic - 주제 이름
 * @returns {Promise} 수집된 응답 배열
 */
export const collectResponses = async (topic) => {
  const response = await axios.post(`${API_BASE_URL}/collect-responses`, null, {
    params: { topic },
  });
  return response.data;
};

/**
 * 자유 토론을 시작합니다.
 */
export const startFreeDiscussion = async () => {
  await axios.post(`${API_BASE_URL}/free-discussion`);
};

/**
 * 토론 회의를 요약합니다.
 * @param {Array} topics - 토론 주제 배열
 * @param {Array} allResponses - 각 주제별 응답 배열
 */
export const summarizeMeeting = async (topics, allResponses) => {
  await axios.post(`${API_BASE_URL}/summarize-meeting`, { topics, allResponses }, {
    headers: { "Content-Type": "application/json" },
  });
};
