# AI Mock Interview Platform - Backend

## Overview

The backend is built using a microservices architecture with Spring Boot and Spring Cloud.

It provides authentication, interview management, AI-powered question generation, answer evaluation, resume parsing, report generation, and service discovery.

---

# Architecture

```
                +----------------+
                | React Frontend |
                +--------+-------+
                         |
                         |
                  API Gateway
                         |
     ---------------------------------------
     |                |                    |
 User Service   Interview Service     AI Service
                         |
                      MySQL
```

---

# Microservices

## 1. User Service

### Responsibilities

- User Registration
- User Login
- JWT Token Generation
- User Profile API

### Technologies

- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL

### APIs

POST /api/auth/register

POST /api/auth/login

GET /api/auth/profile

---

## 2. Interview Service

### Responsibilities

- Start Interview
- Upload Resume
- Resume Parsing
- Store Resume
- Store Interview Session
- Store Questions
- Store Answers
- Generate Interview Report
- Interview History

### Technologies

- Spring Boot
- OpenFeign
- Spring Data JPA
- MySQL

### APIs

POST /api/interviews/start

POST /api/interviews/upload-resume

POST /api/interviews/answer

GET /api/reports/{interviewId}

GET /api/interviews/history

---

## 3. AI Service

### Responsibilities

- Generate Interview Questions
- Evaluate Candidate Answers
- Generate Next Question
- Generate Final Report

### AI Provider

- Groq API

---

## Infrastructure

### API Gateway

Responsibilities

- Single Entry Point
- Routing Requests
- CORS

---

### Eureka Server

Responsibilities

- Service Discovery
- Microservice Registration

---

# Database Design

## interview_sessions

Stores

- User Email
- Job Role
- Job Description
- Resume
- Resume Text
- Current Question
- Total Questions
- Interview Status
- Created Date

---

## questions

Stores

- Question
- Question Number
- Interview

---

## answers

Stores

- Question
- Candidate Answer
- Score
- Feedback

---

## reports

Stores

- Overall Score
- Strengths
- Weaknesses
- Recommendations

---

# Interview Flow

User Login

↓

Start Interview

↓

Upload Resume

↓

Resume Parsing

↓

AI Generates Question

↓

Store Question

↓

Candidate Answers

↓

AI Evaluates

↓

Store Answer

↓

Generate Next Question

↓

Repeat 10 Questions

↓

Generate Final Report

↓

Store Report

↓

Return Report

---

# Features Implemented

## Authentication

- User Registration
- User Login
- JWT Generation

## Resume

- Resume Upload
- PDF Parsing

## Interview

- AI Question Generation
- AI Answer Evaluation
- Dynamic Question Flow
- Answer Scoring
- Feedback

## Report

- AI Generated Report
- Strengths
- Weaknesses
- Recommendations

## History

- View Previous Interviews
- View Previous Reports

---

# Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Eureka
- OpenFeign
- MySQL
- Maven
- Groq API

---

# Future Improvements

## Security

- JWT Validation in all Microservices
- User-based History
- Role-based Authorization

## Features

- Resume Versioning
- Interview Scheduling
- Voice Interview
- Webcam Interview
- Coding Interview Support
- Download Report as PDF
- Email Report
- Analytics Dashboard

## Performance

- Redis Cache
- Docker Compose
- Kubernetes
- CI/CD Pipeline
- Monitoring with Grafana & Prometheus
