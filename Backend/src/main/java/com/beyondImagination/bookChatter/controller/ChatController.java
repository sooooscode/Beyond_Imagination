package com.beyondImagination.bookChatter.controller;

import com.beyondImagination.AI.RAGBookClubGPT; // Update the import to RAGBookClubGPT

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

  @Autowired
  private RAGBookClubGPT ragBookClubGPT; // Change the type to RAGBookClubGPT

  @GetMapping("/")
  public String home() {
    return "Welcome to the Chat Service!";
  }

  @PostMapping("/generate-topics")
  public void generateTopics(@RequestBody List<String> participantsResponses) {
    ragBookClubGPT.generateTopics(); // Call methods from RAGBookClubGPT
  }

  @PostMapping("/collect-responses")
  public List<String> collectResponses(@RequestParam String topic) {
    return ragBookClubGPT.collectResponses(topic); // Call methods from RAGBookClubGPT
  }

  @PostMapping("/free-discussion")
  public void freeDiscussion() {
    ragBookClubGPT.freeDiscussion(); // Call methods from RAGBookClubGPT
  }

  @PostMapping("/summarize-meeting")
  public void summarizeMeeting() {
    ragBookClubGPT.summarizeMeeting(); // Call methods from RAGBookClubGPT
  }
}
