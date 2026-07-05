package com.interview.interviewservice.service;

import com.interview.interviewservice.dto.ReportResponse;
import com.interview.interviewservice.dto.ai.ReportGenerationRequest;
import com.interview.interviewservice.dto.ai.ReportGenerationResponse;
import com.interview.interviewservice.entity.Answer;
import com.interview.interviewservice.entity.InterviewSession;
import com.interview.interviewservice.entity.Report;
import com.interview.interviewservice.feign.AIServiceClient;
import com.interview.interviewservice.repository.AnswerRepository;
import com.interview.interviewservice.repository.InterviewRepository;
import com.interview.interviewservice.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final InterviewRepository interviewRepository;
    private final AnswerRepository answerRepository;
    private final ReportRepository reportRepository;
    private final AIServiceClient aiServiceClient;

    public ReportResponse generateReport(Long interviewId) {

        InterviewSession interviewSession = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        // Return existing report if already generated
        Report existingReport = reportRepository
                .findByInterviewSessionId(interviewId)
                .orElse(null);

        if (existingReport != null) {

            return new ReportResponse(
                    existingReport.getOverallScore(),
                    existingReport.getStrengths(),
                    existingReport.getWeaknesses(),
                    existingReport.getRecommendations()
            );
        }

        List<Answer> answers =
                answerRepository.findByQuestionInterviewSessionId(interviewId);

        StringBuilder transcript = new StringBuilder();

        int count = 1;

        for (Answer answer : answers) {

            transcript.append("Question ")
                    .append(count)
                    .append(":\n");

            transcript.append(answer.getQuestion().getQuestion())
                    .append("\n\n");

            transcript.append("Answer:\n")
                    .append(answer.getAnswer())
                    .append("\n\n");

            transcript.append("Score:\n")
                    .append(answer.getScore())
                    .append("\n\n");

            transcript.append("Feedback:\n")
                    .append(answer.getFeedback())
                    .append("\n\n");

            transcript.append("------------------------------------------\n\n");

            count++;
        }

        ReportGenerationRequest request = new ReportGenerationRequest();

        request.setJobRole(interviewSession.getJobRole());
        request.setJobDescription(interviewSession.getJobDescription());
        request.setResumeText(interviewSession.getResumeText());
        request.setInterviewData(transcript.toString());

        ReportGenerationResponse response =
                aiServiceClient.generateReport(request);

        Report report = Report.builder()
                .interviewSession(interviewSession)
                .overallScore(response.getOverallScore())
                .strengths(response.getStrengths())
                .weaknesses(response.getWeaknesses())
                .recommendations(response.getRecommendations())
                .build();

        reportRepository.save(report);

        return new ReportResponse(
                response.getOverallScore(),
                response.getStrengths(),
                response.getWeaknesses(),
                response.getRecommendations()
        );
    }
}