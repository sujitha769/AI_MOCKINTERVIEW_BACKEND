package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class InterviewHistoryResponse {

    private Long interviewId;

    private String jobRole;

    private Double overallScore;

    private Boolean completed;

    private LocalDateTime createdAt;

}