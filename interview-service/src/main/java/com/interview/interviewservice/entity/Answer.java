package com.interview.interviewservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interview_id", nullable = false)
    private InterviewSession interviewSession;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String answer;

    @Column(nullable = false)
    private Double score;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String feedback;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}