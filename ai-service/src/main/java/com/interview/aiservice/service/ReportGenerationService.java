package com.interview.aiservice.service;

import com.interview.aiservice.dto.ReportGenerationRequest;
import com.interview.aiservice.dto.ReportGenerationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportGenerationService {

    private final GroqService groqService;
    private final JsonParserService jsonParserService;

    public ReportGenerationResponse generate(
            ReportGenerationRequest request) {

        String json = groqService.generateReport(
                request.getJobRole(),
                request.getJobDescription(),
                request.getResumeText(),
                request.getInterviewData()
        );

        return jsonParserService.parseReport(json);

    }

}