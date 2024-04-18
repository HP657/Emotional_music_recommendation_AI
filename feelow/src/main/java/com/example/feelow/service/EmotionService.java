package com.example.feelow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionService {

    @Autowired
    private PostService postService;

    public String processEmotion(String sentence) {
        // 서버의 주소
        String serverUrl = "https://feelow-ai.run.goorm.site/classify";

        // 보낼 데이터
        String jsonData = "{\"sentence\": \"" + sentence + "\"}";

        // POST 요청 보내기
        return postService.sendPostRequest(serverUrl, jsonData, "Emotion 분석");
    }
}
