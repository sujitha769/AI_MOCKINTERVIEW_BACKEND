package com.interview.interviewservice.service;

import com.interview.interviewservice.dto.InterviewHistoryResponse;
import com.interview.interviewservice.entity.InterviewSession;
import com.interview.interviewservice.entity.Report;
import com.interview.interviewservice.repository.InterviewRepository;
import com.interview.interviewservice.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final InterviewRepository interviewRepository;
    private final ReportRepository reportRepository;

    public List<InterviewHistoryResponse> getHistory() {

        String userEmail = "suji@gmail.com";

        List<InterviewSession> interviews =
                interviewRepository.findByUserEmailAndCompletedTrueOrderByCreatedAtDesc(userEmail);

        List<InterviewHistoryResponse> history = new ArrayList<>();

        for (InterviewSession interview : interviews) {

            Double overallScore = null;

            Report report = reportRepository
                    .findByInterviewSessionId(interview.getId())
                    .orElse(null);

            if (report != null) {
                overallScore = report.getOverallScore();
            }

            history.add(

                    new InterviewHistoryResponse(

                            interview.getId(),

                            interview.getJobRole(),

                            overallScore,

                            interview.getCompleted(),

                            interview.getCreatedAt()

                    )

            );

        }

        return history;

    }

}