//Model
//메시지 데이터(발신자, 내용)를 관리하는 모델 클래스.
//메시지 구조를 정의하고, 클라이언트와 데이터를 주고받을 때 활용.


package com.example.chat;

public class ChatMessage {
    private String sender;
    private String content;

    public ChatMessage() {}

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
