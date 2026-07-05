package com.interview.interviewservice.repository;

import com.interview.interviewservice.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<InterviewSession, Long> {

    List<InterviewSession> findByUserEmail(String userEmail);

    List<InterviewSession> findByUserEmailAndCompletedTrueOrderByCreatedAtDesc(String userEmail);

}