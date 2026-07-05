package com.interview.interviewservice.dto.ai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIRequest {

    private String jobRole;

    private String jobDescription;

    private String resumeText;

}