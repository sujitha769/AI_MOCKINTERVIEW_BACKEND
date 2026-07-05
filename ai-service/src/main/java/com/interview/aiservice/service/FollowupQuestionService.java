package com.interview.aiservice.service;

import com.interview.aiservice.dto.FollowupQuestionRequest;
import com.interview.aiservice.dto.FollowupQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowupQuestionService {

    private final GroqService groqService;

    public FollowupQuestionResponse generate(
            FollowupQuestionRequest request) {

        String question = groqService.generateFollowupQuestion(
                request.getJobRole(),
                request.getPreviousQuestion(),
                request.getCandidateAnswer(),
                request.getScore()
        );

        return new FollowupQuestionResponse(question);
    }
}