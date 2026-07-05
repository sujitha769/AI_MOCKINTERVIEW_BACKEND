package com.interview.interviewservice.controller;

import com.interview.interviewservice.dto.InterviewResponse;
import com.interview.interviewservice.dto.ResumeUploadResponse;
import com.interview.interviewservice.dto.StartInterviewRequest;
import com.interview.interviewservice.dto.SubmitAnswerRequest;
import com.interview.interviewservice.dto.SubmitAnswerResponse;
import com.interview.interviewservice.service.AnswerService;
import com.interview.interviewservice.service.InterviewService;
import com.interview.interviewservice.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor

public class InterviewController {

    private final InterviewService interviewService;
    private final ResumeService resumeService;
    private final AnswerService answerService;

    @PostMapping("/start")
    public ResponseEntity<InterviewResponse> startInterview(
            @Valid @RequestBody StartInterviewRequest request) {

        String userEmail = "suji@gmail.com";

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(interviewService.startInterview(request, userEmail));
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<ResumeUploadResponse> uploadResume(
            @RequestParam Long interviewId,
            @RequestParam MultipartFile resume)
            throws IOException {

        return ResponseEntity.ok(
                resumeService.uploadResume(interviewId, resume)
        );
    }

    @PostMapping("/answer")
    public ResponseEntity<SubmitAnswerResponse> submitAnswer(
            @Valid @RequestBody SubmitAnswerRequest request) {

        return ResponseEntity.ok(
                answerService.submitAnswer(request)
        );
    }
}