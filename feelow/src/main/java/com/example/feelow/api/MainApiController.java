package com.example.feelow.api;

import com.example.feelow.dto.EmotionRequest;
import com.example.feelow.dto.EmotionResponse;
import com.example.feelow.service.EmotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class MainApiController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping("/api/recommend")
    public EmotionResponse recommend(@RequestBody EmotionRequest request) {
        log.debug("Request received: {}", request);
        String emotion = request.getEmotion();
        log.info("Processing emotion: {}", emotion);
        String recommendation = emotionService.processEmotion(emotion);
        log.info("Generated recommendation: {}", recommendation);
        return new EmotionResponse(recommendation);
    }
}
