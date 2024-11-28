//Controller
//클라이언트가 보낸 메시지(/app/sendMessage)를 처리.
//메시지가 도착하면 해당 메시지를 모든 구독자에게 브로드캐스트(/topic/messages).

//챗봇 로직 구현:
// 메시지 수가 특정 조건에 도달하면 챗봇이 주제를 전송.
// 1분마다 챗봇이 질문을 자동으로 전송.


package com.example.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private static final int MAX_USERS = 4;
    private static final int MAX_TOPICS = 4;

    private List<String> messages = new ArrayList<>();
    private int topicCounter = 1;
    private boolean waitingForResponse = false;

    // 사용자 메시지 처리
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String handleUserMessage(String message) {
        messages.add(message);

        // 4명의 메시지가 도착하면 챗봇 응답
        if (messages.size() == MAX_USERS && !waitingForResponse) {
            waitingForResponse = true;
            return "주제" + topicCounter;
        }

        return message; // 사용자 메시지를 그대로 반환
    }

    // 1분 후 챗봇 질문
    @Scheduled(fixedDelay = 60000)
    @SendTo("/topic/messages")
    public String chatbotResponse() {
        if (waitingForResponse && topicCounter <= MAX_TOPICS) {
            return "다음 주제로 넘어가시겠습니까?";
        }
        return null;
    }

    // 모든 사용자가 "예"를 응답하면 다음 주제로 이동
    public void processUserConsent(String message) {
        if (message.equalsIgnoreCase("예") && messages.size() == MAX_USERS) {
            topicCounter++;
            messages.clear();
            waitingForResponse = false;
        }
    }
}
