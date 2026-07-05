package com.interview.interviewservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "interview_id", nullable = false)
    private InterviewSession interviewSession;

    @Column(nullable = false)
    private Double overallScore;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String strengths;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String weaknesses;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String recommendations;

}