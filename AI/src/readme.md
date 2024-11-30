```
book-club-ai/
│
├── src/
│   ├── RAGBookClubGPT.java        # 주요 로직
│   ├── PDFLoader.java             # PDF 로딩 및 처리
│   ├── TextProcessor.java         # 텍스트 처리 및 필터링
│   ├── ResponseFilter.java        # 응답 필터링
│   ├── DiscussionManager.java     # 토론 관리
│   └── Main.java                  # 프로그램 실행
│
├── lib/
│   └── external-libraries.jar     # 외부 라이브러리 (예: OpenAI API 연결)
│
└── README.md                     # 프로젝트 설명
```

</br>
**RAGBookClubGPT.java:**</br>
RAGBookClubGPT 클래스의 주요 로직을 포함. 이 클래스는 PDF 로딩, 응답 수집, 주제 생성, 자유 토론, 요약 생성 등을 담당</br>

**PDFLoader.java:**</br>
PDF 파일을 로드하는 로직을 이 클래스에서 관리. PyPDFLoader와 비슷한 역할.</br>

**TextProcessor.java:**</br>
텍스트 데이터 처리 관련 함수들을 포함. 예를 들어, 감상문 수집 후 텍스트를 결합하거나, 토픽 생성에 필요한 컨텍스트를 준비하는 로직이 포함.</br>

**ResponseFilter.java:**</br>
응답에서 욕설과 비방을 필터링하는 로직을 포함. bad_words 목록을 기반으로 필터링 작업.</br>

**DiscussionManager.java:**</br>
토론 관리</br>

**Main.java:**</br>
각 기능을 테스트하거나 실행</br>
