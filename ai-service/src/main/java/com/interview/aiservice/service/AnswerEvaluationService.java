package com.interview.aiservice.service;

import com.interview.aiservice.dto.AnswerEvaluationRequest;
import com.interview.aiservice.dto.AnswerEvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerEvaluationService {

    private final GroqService groqService;
    private final JsonParserService jsonParserService;

    public AnswerEvaluationResponse evaluate(
            AnswerEvaluationRequest request) {

        String json = groqService.evaluateAnswer(
                request.getQuestion(),
                request.getAnswer()
        );

        return jsonParserService.parseEvaluation(json);

    }

}