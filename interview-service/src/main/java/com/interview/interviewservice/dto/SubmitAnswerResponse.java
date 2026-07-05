package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubmitAnswerResponse {

    private Double score;

    private String feedback;

    private Long nextQuestionId;

    private String nextQuestion;

}