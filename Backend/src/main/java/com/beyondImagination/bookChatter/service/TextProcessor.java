package com.beyondImagination.bookChatter.service;

import java.util.List;

public class TextProcessor {

    public String combineText(List<String> texts) {
        StringBuilder combinedText = new StringBuilder();
        for (String text : texts) {
            combinedText.append(text).append("\n");
        }
        return combinedText.toString();
    }

    public List<String> prepareContextForTopics(List<String> contextDocs) {
        // 주제 생성을 위한 텍스트 전처리 로직
        return contextDocs; // 예시로 그대로 반환 (실제 구현에서는 텍스트 처리 로직 필요)
    }
}
