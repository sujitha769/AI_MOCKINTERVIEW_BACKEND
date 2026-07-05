package com.interview.aiservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIRequest {

    @NotBlank(message = "Job role is required")
    private String jobRole;

    @NotBlank(message = "Job description is required")
    private String jobDescription;

    @NotBlank(message = "Resume text is required")
    private String resumeText;

}