package com.example.feelow.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmotionService {

    public String processEmotion(String emotion) {
        // 감정을 처리하는 로직 구현
        switch (emotion.toLowerCase()) {
            case "행복":
                return "행복할 때 듣기 좋은 노래: [노래 제목]";
            case "슬픔":
                return "슬픔을 위로하는 노래: [노래 제목]";
            default:
                return "이 감정에 맞는 노래를 찾을 수 없습니다.";
        }
    }

    public String getRecommendationFromPython(String emotion) {
        // 파이썬 API 연동
        RestTemplate restTemplate = new RestTemplate();
        String pythonApiUrl = "http://localhost:5000/api/process_emotion"; // 파이썬 API URL 설정
        return restTemplate.postForObject(pythonApiUrl, emotion, String.class);
    }
}
