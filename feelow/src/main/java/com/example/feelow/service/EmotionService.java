package com.example.feelow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionService {

    @Autowired
    private PostService postService;

    public String processEmotion(String sentence) {
        String serverUrl = "https://feelow-ai.run.goorm.site/recommend_song";

        String jsonData = "{\"sentence\": \"" + sentence + "\"}";

        return postService.sendPostRequest(serverUrl, jsonData, "Emotion 분석");
    }
}
