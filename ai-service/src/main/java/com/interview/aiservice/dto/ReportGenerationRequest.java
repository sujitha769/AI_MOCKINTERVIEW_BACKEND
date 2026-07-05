package com.interview.aiservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportGenerationRequest {

    private String jobRole;

    private String jobDescription;

    private String resumeText;

    private String interviewData;

}