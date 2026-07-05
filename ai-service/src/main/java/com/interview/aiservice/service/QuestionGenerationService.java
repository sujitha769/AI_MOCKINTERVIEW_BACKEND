package com.interview.aiservice.service;

import com.interview.aiservice.dto.AIRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionGenerationService {

    private final GroqService groqService;

    public String generateQuestion(AIRequest request) {

        return groqService.generateQuestion(request, List.of());

    }

}