import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { submitFeedbackAPI } from "../../Api";
import "./FeedbackForm.css";

const FeedbackForm = () => {
  const [input, setInput] = useState("");
  const [editedFeedback, setEditedFeedback] = useState("");
  const [remainingEdits, setRemainingEdits] = useState(5); // 남은 첨삭 횟수
  const navigate = useNavigate();

  const handleEdit = async () => {
    if (!input.trim()) {
      alert("소감문을 입력해주세요.");
      return;
    }
    if (remainingEdits <= 0) {
      alert("첨삭 가능 횟수를 모두 사용하셨습니다.");
      return;
    }
    try {
      // Feedback API 호출
      const result = await submitFeedbackAPI(input);
      setEditedFeedback(result.data.editedContent); // 결과값 저장
      setRemainingEdits((prev) => prev - 1); // 남은 횟수 감소
    } catch (error) {
      alert("첨삭 요청에 실패했습니다. 다시 시도해주세요.");
    }
  };

  const handleSubmit = async () => {
    // 확인 대화상자 표시
    const userConfirmation = window.confirm("제출하시겠습니까?");
    if (!userConfirmation) {
      // "취소"를 선택하면 함수 종료
      return;
    }

    // 제출 로직 실행
    if (editedFeedback.trim()) {
      try {
        await submitFeedbackAPI(editedFeedback); // 최종 결과 제출
        alert("소감문이 성공적으로 제출되었습니다.");
      } catch (error) {
        // 실패 처리 주석화
        // alert("소감문 제출에 실패했습니다. 다시 시도해주세요.");
        console.warn("소감문 제출에 실패했습니다:", error);
      } finally {
        navigate("/chat"); // 실패 여부와 상관없이 chat.js로 이동
      }
    } else {
      alert("첨삭된 소감문을 확인해주세요.");
      navigate("/chat"); // 첨삭 결과가 없어도 chat.js로 이동
    }
  };

  // 초기화 함수
  const handleReset = () => {
    setInput(""); // 입력값 초기화
    setEditedFeedback(""); // 수정된 결과 초기화
    setRemainingEdits(5); // 남은 첨삭 횟수 초기화
  };

  return (
    <div className="feedback-form">
      <h1>소감문 작성</h1>
      <div className="textarea-container">
        {/* 왼쪽 사용자 입력 */}
        <textarea
          placeholder="소감문을 입력해주세요. 첨삭은 최대 5번 가능합니다!"
          value={input}
          onChange={(e) => setInput(e.target.value)}
        />
        {/* 오른쪽 결과 출력 */}
        <textarea
          placeholder="이렇게 써보는 건 어떨까요?"
          value={editedFeedback}
          readOnly
        />
      </div>
      <div className="remaining-count">남은 첨삭 수: {remainingEdits}/5</div>
      <div className="actions">
        <button onClick={handleReset}>초기화</button> {/* 초기화 버튼 */}
        <button onClick={handleEdit}>동의 후 첨삭</button>
        <button onClick={handleSubmit}>제출</button> {/* 제출 버튼 */}
      </div>
    </div>
  );
};

export default FeedbackForm;
