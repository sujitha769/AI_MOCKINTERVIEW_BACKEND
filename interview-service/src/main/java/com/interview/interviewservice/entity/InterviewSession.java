package com.interview.interviewservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interview_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String jobRole;

    @Lob
    @Column(nullable = false)
    private String jobDescription;

    @Column
    private String resumeFilePath;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String resumeText;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Integer currentQuestion;

    @Column(nullable = false)
    private Boolean completed;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}