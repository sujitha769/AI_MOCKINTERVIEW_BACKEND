package com.interview.interviewservice.dto.ai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportGenerationResponse {

    private Double overallScore;

    private String strengths;

    private String weaknesses;

    private String recommendations;

}