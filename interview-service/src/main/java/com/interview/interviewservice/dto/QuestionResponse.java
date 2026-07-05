package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionResponse {

    private Integer questionNumber;
    private String question;

}