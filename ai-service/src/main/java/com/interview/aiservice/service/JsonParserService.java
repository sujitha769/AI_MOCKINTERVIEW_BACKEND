package com.interview.aiservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.aiservice.dto.AnswerEvaluationResponse;
import com.interview.aiservice.dto.ReportGenerationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonParserService {

    private final ObjectMapper objectMapper;

    public AnswerEvaluationResponse parseEvaluation(String json) {

        try {
            return objectMapper.readValue(
                    json,
                    AnswerEvaluationResponse.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }

    }

    public ReportGenerationResponse parseReport(String json) {

        try {

            return objectMapper.readValue(
                    json,
                    ReportGenerationResponse.class
            );

        } catch (Exception e) {

            throw new RuntimeException("Failed to parse report", e);

        }

    }
}