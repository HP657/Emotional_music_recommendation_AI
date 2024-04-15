package com.example.feelow.api;

import com.example.feelow.dto.EmotionRequest;
import com.example.feelow.dto.EmotionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainApiController {

    @PostMapping("/api/recommend")
    public EmotionResponse recommend(@RequestBody EmotionRequest request) {
        String recommendation = processEmotion(request.getEmotion());
        return new EmotionResponse(recommendation);
    }

    private String processEmotion(String emotion) {
        switch (emotion.toLowerCase()) {
            case "행복":
                return "행복한 노래 추천: [노래제목]";
            case "슬픔":
                return "슬픈 노래 추천: [노래제목]";
            default:
                return "이 감정에 맞는 노래를 찾을 수 없습니다.";
        }
    }
}
