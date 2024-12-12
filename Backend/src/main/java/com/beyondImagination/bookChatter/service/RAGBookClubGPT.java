package com.beyondImagination.bookChatter.service;

public class RAGBookClubGPT {
    private PDFLoader pdfLoader;
    private TextProcessor textProcessor;
    private ResponseFilter responseFilter;
    private DiscussionManager discussionManager;

    // 생성자
    public RAGBookClubGPT(String pdfPath) {
        this.pdfLoader = new PDFLoader(pdfPath);
        this.textProcessor = new TextProcessor();
        this.responseFilter = new ResponseFilter();
        this.discussionManager = new DiscussionManager();
    }

    public void generateTopics(List<String> userResponses) {
        String combinedText = textProcessor.combineText(userResponses);
        List<String> topics = textProcessor.prepareContextForTopics(Collections.singletonList(combinedText));
        discussionManager.createTopics(topics);
    }

    public List<String> collectResponses(String topic) {
        return discussionManager.collectUserResponses(topic);
    }

    public void startFreeDiscussion() {
        discussionManager.startFreeDiscussion();
    }

    public void summarizeMeeting(List<String> topics, List<List<String>> allResponses) {
        discussionManager.summarizeMeeting(topics, allResponses);
    }
}
