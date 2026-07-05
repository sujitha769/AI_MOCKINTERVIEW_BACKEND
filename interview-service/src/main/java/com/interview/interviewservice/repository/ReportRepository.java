package com.interview.interviewservice.repository;

import com.interview.interviewservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByInterviewSessionId(Long interviewId);


}