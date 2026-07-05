package com.interview.interviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumeUploadResponse {

    private String message;

    private Long questionId;

    private String question;

}