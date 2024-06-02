package com.example.feelow.api;

import com.example.feelow.dto.EmotionRequest;
import com.example.feelow.dto.EmotionResponse;
import com.example.feelow.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainApiController {

    @Autowired
    private EmotionService emotionService;

    @PostMapping("/recommend")
    public EmotionResponse recommend(@RequestBody EmotionRequest request) {
        String sentence = request.getSentence();
        String recommendation = emotionService.processEmotion(sentence);
        return new EmotionResponse(recommendation);
    }
}
