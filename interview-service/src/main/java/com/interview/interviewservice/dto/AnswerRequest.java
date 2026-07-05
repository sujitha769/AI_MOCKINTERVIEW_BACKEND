package com.interview.interviewservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {

    private Long questionId;

    @NotBlank(message = "Answer is required")
    private String answer;

}