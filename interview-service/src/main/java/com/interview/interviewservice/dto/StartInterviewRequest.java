package com.interview.interviewservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartInterviewRequest {

    @NotBlank(message = "Job role is required")
    private String jobRole;

    @NotBlank(message = "Job description is required")
    private String jobDescription;

}