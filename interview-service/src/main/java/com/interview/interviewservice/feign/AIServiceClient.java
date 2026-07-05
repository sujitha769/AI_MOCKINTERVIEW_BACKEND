package com.interview.interviewservice.feign;

import com.interview.interviewservice.dto.ai.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AI-SERVICE")
public interface AIServiceClient {

    @PostMapping("/api/ai/generate-question")
    AIResponse generateQuestion(@RequestBody AIRequest request);

    @PostMapping("/api/ai/evaluate-answer")
    AnswerEvaluationResponse evaluateAnswer(
            @RequestBody AnswerEvaluationRequest request
    );

    @PostMapping("/api/ai/generate-followup-question")
    FollowupQuestionResponse generateFollowupQuestion(
            @RequestBody FollowupQuestionRequest request
    );

    @PostMapping("/api/ai/generate-report")
    ReportGenerationResponse generateReport(
            @RequestBody ReportGenerationRequest request
    );
}