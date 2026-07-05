package com.interview.interviewservice.service;

import com.interview.interviewservice.dto.ResumeUploadResponse;
import com.interview.interviewservice.dto.ai.AIRequest;
import com.interview.interviewservice.dto.ai.AIResponse;
import com.interview.interviewservice.entity.InterviewSession;
import com.interview.interviewservice.entity.Question;
import com.interview.interviewservice.feign.AIServiceClient;
import com.interview.interviewservice.repository.InterviewRepository;
import com.interview.interviewservice.repository.QuestionRepository;
import com.interview.interviewservice.util.PdfExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final InterviewRepository interviewRepository;
    private final QuestionRepository questionRepository;
    private final PdfExtractor pdfExtractor;
    private final AIServiceClient aiServiceClient;

    public ResumeUploadResponse uploadResume(Long interviewId,
                                             MultipartFile resume) throws IOException {

        InterviewSession interviewSession = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();

        Path uploadPath = Paths.get("uploads");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                resume.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        String resumeText = pdfExtractor.extractText(filePath.toString());

        interviewSession.setResumeFilePath(filePath.toString());
        interviewSession.setResumeText(resumeText);

        interviewRepository.save(interviewSession);

        AIRequest aiRequest = new AIRequest();
        aiRequest.setJobRole(interviewSession.getJobRole());
        aiRequest.setJobDescription(interviewSession.getJobDescription());
        aiRequest.setResumeText(resumeText);

        AIResponse aiResponse = aiServiceClient.generateQuestion(aiRequest);

        Question question = Question.builder()
                .interviewSession(interviewSession)
                .question(aiResponse.getResponse())
                .questionNumber(1)
                .build();

        Question savedQuestion = questionRepository.save(question);

        return new ResumeUploadResponse(
                "Resume uploaded successfully.",
                savedQuestion.getId(),
                savedQuestion.getQuestion()
        );
    }
}