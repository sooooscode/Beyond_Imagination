package com.beyondImagination.bookChatter.service;

import java.util.List;

public class DiscussionManager {

    public void createTopics(List<String> topics) {
        // 주제 생성 로직
        for (String topic : topics) {
            System.out.println("새로운 토픽 생성: " + topic);
        }
    }

    public List<String> collectUserResponses(String topic) {
        // 사용자 응답 수집 (예시로 고정된 응답을 반환)
        return List.of("응답1", "응답2", "응답3");
    }

    public void startFreeDiscussion() {
        // 자유 토론 시작
        System.out.println("자유 토론 시작!");
    }

    public void summarizeMeeting(List<String> topics, List<List<String>> allResponses) {
        // 토론 요약
        System.out.println("토론 요약:");
        for (int i = 0; i < topics.size(); i++) {
            System.out.println("주제: " + topics.get(i));
            System.out.println("응답: " + String.join(", ", allResponses.get(i)));
        }
    }
}
