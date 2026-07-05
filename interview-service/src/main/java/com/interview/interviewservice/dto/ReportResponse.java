package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportResponse {

    private Double overallScore;

    private String strengths;

    private String weaknesses;

    private String recommendations;

}