package com.example.feelow.api;

import com.example.feelow.dto.EmotionRequest;
import com.example.feelow.dto.EmotionResponse;
import com.example.feelow.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainApiController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping("/api/recommend")
    public EmotionResponse recommend(@RequestBody EmotionRequest request) {
        String emotion = request.getEmotion();
        String recommendation = emotionService.processEmotion(emotion);
        // String recommendation = emotionService.getRecommendationFromPython(emotion); // 파이썬 API 사용 시
        return new EmotionResponse(recommendation);
    }
}
