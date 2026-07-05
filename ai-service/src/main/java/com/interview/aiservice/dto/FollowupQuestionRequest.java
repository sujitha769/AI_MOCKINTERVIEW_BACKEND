package com.interview.aiservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowupQuestionRequest {

    private String jobRole;

    private String previousQuestion;

    private String candidateAnswer;

    private Double score;

}