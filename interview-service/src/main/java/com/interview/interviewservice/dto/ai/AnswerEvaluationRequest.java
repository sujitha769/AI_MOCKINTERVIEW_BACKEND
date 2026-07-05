package com.interview.interviewservice.dto.ai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerEvaluationRequest {

    private String question;
    private String answer;

}