package com.interview.interviewservice.repository;

import com.interview.interviewservice.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionInterviewSessionId(Long interviewId);

}