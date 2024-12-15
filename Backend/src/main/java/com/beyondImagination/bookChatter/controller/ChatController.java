package com.beyondImagination.bookChatter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.beyondImagination.bookChatter.service.RAGBookClubGPT;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

  @Autowired
  private RAGBookClubGPT ragBookClubGPT; // RAGBookClubGPT 타입으로 변경

  @GetMapping("/")
  public String home() {
    return "Welcome to the Chat Service!";
  }

  @PostMapping("/generate-topics")
  public void generateTopics(@RequestBody List<String> participantsResponses) {
    // 사용자 응답을 기반으로 주제 생성
    ragBookClubGPT.generateTopics(participantsResponses); // generateTopics 메서드 호출
  }

  @PostMapping("/collect-responses")
  public List<String> collectResponses(@RequestParam String topic) {
    // 주제에 대한 사용자 응답 수집
    return ragBookClubGPT.collectResponses(topic); // collectResponses 메서드 호출
  }

  @PostMapping("/free-discussion")
  public void freeDiscussion() {
    // 자유 토론 시작
    ragBookClubGPT.startFreeDiscussion(); // startFreeDiscussion 메서드 호출
  }

  @PostMapping("/summarize-meeting")
  public void summarizeMeeting(@RequestBody List<String> topics, @RequestBody List<List<String>> allResponses) {
    // 회의 요약
    ragBookClubGPT.summarizeMeeting(topics, allResponses); // summarizeMeeting 메서드 호출
  }
}