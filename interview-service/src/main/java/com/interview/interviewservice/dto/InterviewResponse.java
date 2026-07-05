package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterviewResponse {

    private Long interviewId;
    private String message;

}