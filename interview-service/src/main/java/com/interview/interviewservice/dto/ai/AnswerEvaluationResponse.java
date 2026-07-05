package com.interview.interviewservice.dto.ai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerEvaluationResponse {

    private Double score;

    private String feedback;

    private String nextQuestion;

}