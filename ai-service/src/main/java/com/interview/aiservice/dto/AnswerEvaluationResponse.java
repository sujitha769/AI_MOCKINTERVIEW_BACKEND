package com.interview.aiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerEvaluationResponse {

    private Double score;

    private String feedback;

    private String nextQuestion;

}