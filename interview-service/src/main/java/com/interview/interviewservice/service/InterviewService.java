package com.interview.interviewservice.service;

import com.interview.interviewservice.dto.InterviewResponse;
import com.interview.interviewservice.dto.ResumeUploadResponse;
import com.interview.interviewservice.dto.StartInterviewRequest;
import com.interview.interviewservice.entity.InterviewSession;
import com.interview.interviewservice.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewResponse startInterview(StartInterviewRequest request, String userEmail) {

        InterviewSession interviewSession = InterviewSession.builder()
                .userEmail(userEmail)
                .jobRole(request.getJobRole())
                .jobDescription(request.getJobDescription())
                .createdAt(LocalDateTime.now())

                .completed(false)
                .currentQuestion(1)
                .totalQuestions(10)

                .build();

        InterviewSession savedInterview = interviewRepository.save(interviewSession);

        return new InterviewResponse(
                savedInterview.getId(),
                "Interview started successfully"
        );
    }
}
