package com.interview.interviewservice.service;

import com.interview.interviewservice.dto.SubmitAnswerRequest;
import com.interview.interviewservice.dto.SubmitAnswerResponse;
import com.interview.interviewservice.dto.ai.AnswerEvaluationRequest;
import com.interview.interviewservice.dto.ai.AnswerEvaluationResponse;
import com.interview.interviewservice.dto.ai.FollowupQuestionRequest;
import com.interview.interviewservice.dto.ai.FollowupQuestionResponse;
import com.interview.interviewservice.entity.Answer;
import com.interview.interviewservice.entity.InterviewSession;
import com.interview.interviewservice.entity.Question;
import com.interview.interviewservice.feign.AIServiceClient;
import com.interview.interviewservice.repository.AnswerRepository;
import com.interview.interviewservice.repository.InterviewRepository;
import com.interview.interviewservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final InterviewRepository interviewRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AIServiceClient aiServiceClient;

    public SubmitAnswerResponse submitAnswer(SubmitAnswerRequest request) {

        InterviewSession interviewSession = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        if (Boolean.TRUE.equals(interviewSession.getCompleted())) {
            throw new RuntimeException("Interview is already completed.");
        }

        Question currentQuestion = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // -----------------------------
        // Evaluate Candidate Answer
        // -----------------------------
        AnswerEvaluationRequest evaluationRequest = new AnswerEvaluationRequest();
        evaluationRequest.setQuestion(currentQuestion.getQuestion());
        evaluationRequest.setAnswer(request.getAnswer());

        AnswerEvaluationResponse evaluationResponse =
                aiServiceClient.evaluateAnswer(evaluationRequest);

        // -----------------------------
        // Save Candidate Answer
        // -----------------------------
        Answer answer = Answer.builder()
                .interviewSession(interviewSession)
                .question(currentQuestion)
                .answer(request.getAnswer())
                .score(evaluationResponse.getScore())
                .feedback(evaluationResponse.getFeedback())
                .createdAt(LocalDateTime.now())
                .build();

        answerRepository.save(answer);

        // -----------------------------
        // Move To Next Question
        // -----------------------------
        interviewSession.setCurrentQuestion(
                interviewSession.getCurrentQuestion() + 1
        );

        // -----------------------------
        // Interview Finished
        // -----------------------------
        if (interviewSession.getCurrentQuestion() > interviewSession.getTotalQuestions()) {

            interviewSession.setCompleted(true);
            interviewRepository.save(interviewSession);

            return new SubmitAnswerResponse(
                    evaluationResponse.getScore(),
                    evaluationResponse.getFeedback(),
                    null,
                    "Interview Completed"
            );
        }

        // -----------------------------
        // Generate Follow-up Question
        // -----------------------------
        FollowupQuestionRequest followupRequest = new FollowupQuestionRequest();
        followupRequest.setJobRole(interviewSession.getJobRole());
        followupRequest.setPreviousQuestion(currentQuestion.getQuestion());
        followupRequest.setCandidateAnswer(request.getAnswer());
        followupRequest.setScore(evaluationResponse.getScore());

        FollowupQuestionResponse followupResponse =
                aiServiceClient.generateFollowupQuestion(followupRequest);

        // -----------------------------
        // Save Next Question
        // -----------------------------
        Question nextQuestion = Question.builder()
                .interviewSession(interviewSession)
                .question(followupResponse.getQuestion())
                .questionNumber(interviewSession.getCurrentQuestion())
                .build();

        Question savedQuestion = questionRepository.save(nextQuestion);

        interviewRepository.save(interviewSession);

        // -----------------------------
        // Return Response
        // -----------------------------
        return new SubmitAnswerResponse(
                evaluationResponse.getScore(),
                evaluationResponse.getFeedback(),
                savedQuestion.getId(),
                savedQuestion.getQuestion()
        );
    }
}