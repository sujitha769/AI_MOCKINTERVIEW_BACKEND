package com.interview.aiservice.service;

import com.interview.aiservice.client.GroqClient;
import com.interview.aiservice.dto.AIRequest;
import com.interview.aiservice.dto.groq.Choice;
import com.interview.aiservice.dto.groq.GroqRequest;
import com.interview.aiservice.dto.groq.GroqResponse;
import com.interview.aiservice.dto.groq.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroqService {

    private final GroqClient groqClient;
    private final PromptBuilderService promptBuilderService;

    @Value("${groq.model}")
    private String model;

    public String generateQuestion(AIRequest request, List<String> previousQuestions) {

        String prompt = promptBuilderService.buildQuestionPrompt(
                request.getJobRole(),
                request.getJobDescription(),
                request.getResumeText(),
                previousQuestions
        );

        GroqRequest groqRequest = new GroqRequest(
                model,
                List.of(new Message("user", prompt))
        );

        GroqResponse response = groqClient.generateResponse(groqRequest);

        Choice choice = response.getChoices().get(0);

        return choice.getMessage().getContent();
    }

    public String evaluateAnswer(String question, String answer) {

        String prompt = promptBuilderService.buildEvaluationPrompt(
                question,
                answer
        );

        GroqRequest groqRequest = new GroqRequest(
                model,
                List.of(new Message("user", prompt))
        );

        GroqResponse response = groqClient.generateResponse(groqRequest);

        return response.getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    public String generateFollowupQuestion(
            String jobRole,
            String previousQuestion,
            String candidateAnswer,
            Double score) {

        String prompt = promptBuilderService.buildFollowupPrompt(
                jobRole,
                previousQuestion,
                candidateAnswer,
                score
        );

        GroqRequest request = new GroqRequest(
                model,
                List.of(new Message("user", prompt))
        );

        GroqResponse response = groqClient.generateResponse(request);

        return response.getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }


    public String generateReport(
            String jobRole,
            String jobDescription,
            String resumeText,
            String interviewData) {

        String prompt = promptBuilderService.buildReportPrompt(
                jobRole,
                jobDescription,
                resumeText,
                interviewData
        );

        GroqRequest request = new GroqRequest(
                model,
                List.of(new Message("user", prompt))
        );

        GroqResponse response = groqClient.generateResponse(request);

        return response.getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}