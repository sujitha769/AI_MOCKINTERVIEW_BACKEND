package com.interview.aiservice.controller;

import com.interview.aiservice.dto.*;
import com.interview.aiservice.service.AIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AIController {

    private final AIService aiService;

    @PostMapping("/generate-question")
    public ResponseEntity<AIResponse> generateQuestion(
            @Valid @RequestBody AIRequest request) {

        return ResponseEntity.ok(
                aiService.generateQuestion(request)
        );
    }

    @PostMapping("/evaluate-answer")
    public ResponseEntity<AnswerEvaluationResponse> evaluateAnswer(
            @RequestBody AnswerEvaluationRequest request) {

        return ResponseEntity.ok(
                aiService.evaluateAnswer(request)
        );
    }

    @PostMapping("/generate-followup-question")
    public ResponseEntity<FollowupQuestionResponse> generateFollowupQuestion(
            @RequestBody FollowupQuestionRequest request) {

        return ResponseEntity.ok(
                aiService.generateFollowupQuestion(request)
        );
    }

    @PostMapping("/generate-report")
    public ResponseEntity<ReportGenerationResponse> generateReport(
            @RequestBody ReportGenerationRequest request) {

        return ResponseEntity.ok(
                aiService.generateReport(request)
        );
    }

}