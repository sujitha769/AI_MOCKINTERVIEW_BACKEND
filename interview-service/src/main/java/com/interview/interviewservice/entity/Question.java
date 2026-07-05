package com.interview.interviewservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private InterviewSession interviewSession;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String question;

    @Column(nullable = false)
    private Integer questionNumber;
}