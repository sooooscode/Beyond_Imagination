package com.beyondImagination.bookChatter.controller;

import com.beyondImagination.bookChatter.service.BookClubGPT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

  @Autowired
  private BookClubGPT bookClubGPT;

  @GetMapping("/")
  public String home() {
    return "Welcome to the Chat Service!";
  }

  @PostMapping("/generate-topics")
  public void generateTopics(@RequestBody List<String> participantsResponses) {
    bookClubGPT.generateTopics();
  }

  @PostMapping("/collect-responses")
  public List<String> collectResponses(@RequestParam String topic) {
    return bookClubGPT.collectResponses(topic);
  }

  @PostMapping("/free-discussion")
  public void freeDiscussion() {
    bookClubGPT.freeDiscussion();
  }

  @PostMapping("/summarize-meeting")
  public void summarizeMeeting() {
    bookClubGPT.summarizeMeeting();
  }
}
