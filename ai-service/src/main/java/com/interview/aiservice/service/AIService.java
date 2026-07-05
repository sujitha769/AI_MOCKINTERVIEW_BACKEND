package com.interview.aiservice.service;

import com.interview.aiservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final QuestionGenerationService questionGenerationService;
    private final AnswerEvaluationService answerEvaluationService;
    private final FollowupQuestionService followupQuestionService;
    private final ReportGenerationService reportGenerationService;

    public AIResponse generateQuestion(AIRequest request) {
        return new AIResponse(
                questionGenerationService.generateQuestion(request)
        );
    }

    public AnswerEvaluationResponse evaluateAnswer(
            AnswerEvaluationRequest request) {

        return answerEvaluationService.evaluate(request);
    }

    public FollowupQuestionResponse generateFollowupQuestion(
            FollowupQuestionRequest request) {

        return followupQuestionService.generate(request);
    }

    public ReportGenerationResponse generateReport(
            ReportGenerationRequest request) {

        return reportGenerationService.generate(request);
    }
}