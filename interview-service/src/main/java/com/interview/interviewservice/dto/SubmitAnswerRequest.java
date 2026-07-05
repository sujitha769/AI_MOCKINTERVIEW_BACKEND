package com.interview.interviewservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitAnswerRequest {

    @NotNull
    private Long interviewId;

    @NotNull
    private Long questionId;

    @NotBlank
    private String answer;

}