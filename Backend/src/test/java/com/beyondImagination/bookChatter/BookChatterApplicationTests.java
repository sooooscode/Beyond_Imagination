package com.beyondImagination.bookChatter;

import com.beyondImagination.bookChatter.controller.ChatController;
import com.beyondImagination.bookChatter.model.ChatMessage;
import com.beyondImagination.bookChatter.repository.ChatMessageRepository;
import com.beyondImagination.bookChatter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookChatterApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ChatMessageRepository chatMessageRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ChatController chatController;

  @Test
  void contextLoads() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string("Welcome to the Chat Service!"));
  }

  @Test
  void testGenerateTopics() throws Exception {
    String sampleResponses = "[\"책이 너무 재미있었어요!\", \"캐릭터 묘사가 인상적이었어요.\"]";

    mockMvc.perform(post("/chat/generate-topics")
        .contentType(MediaType.APPLICATION_JSON)
        .content(sampleResponses))
        .andExpect(status().isOk());
  }

  @Test
  void testCollectResponses() throws Exception {
    String topic = "흥미로운 캐릭터 묘사";

    mockMvc.perform(post("/chat/collect-responses")
        .param("topic", topic))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(greaterThan(0))));
  }

  @Test
  void testFreeDiscussion() throws Exception {
    mockMvc.perform(post("/chat/free-discussion"))
        .andExpect(status().isOk());
  }

  @Test
  void testSummarizeMeeting() throws Exception {
    String topicsJson = "[\"주제 1\", \"주제 2\"]";
    String responsesJson = "[[\"응답 1\", \"응답 2\"], [\"응답 3\", \"응답 4\"]]";

    mockMvc.perform(post("/chat/summarize-meeting")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"topics\":" + topicsJson + ", \"allResponses\":" + responsesJson + "}"))
        .andExpect(status().isOk());
  }

  @Test
  void testSaveAndRetrieveChatMessage() {
    ChatMessage message = new ChatMessage();
    message.setSender("testUser");
    message.setContent("This is a test message.");
    message.setVisible(true);
    message.setUserMessage(true);

    ChatMessage savedMessage = chatMessageRepository.save(message);

    ChatMessage retrievedMessage = chatMessageRepository.findById(savedMessage.getId()).orElse(null);

    assert retrievedMessage != null;
    assert retrievedMessage.getContent().equals("This is a test message.");
  }

}
