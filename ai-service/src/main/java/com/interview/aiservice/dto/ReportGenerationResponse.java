package com.interview.aiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportGenerationResponse {

    private Double overallScore;

    private String strengths;

    private String weaknesses;

    private String recommendations;

}