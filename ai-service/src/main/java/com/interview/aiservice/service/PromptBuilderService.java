package com.interview.aiservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptBuilderService {

    public String buildQuestionPrompt(String jobRole,
                                      String jobDescription,
                                      String resumeText,
                                      List<String> previousQuestions) {

        String previousQuestionsBlock = (previousQuestions == null || previousQuestions.isEmpty())
                ? "None yet. This is the first question."
                : String.join("\n- ", previousQuestions);

        return """
                You are an experienced technical interviewer conducting a real, spoken interview for a %s role.

                Job Description:
                %s

                Candidate Resume:
                %s

                Questions already asked in this interview (do NOT repeat these topics or rephrase them):
                - %s

                Generate ONLY ONE new interview question.

                Rules:
                - The question must be short (1-2 sentences), natural, and conversational — the way a real interviewer speaks out loud.
                - Do NOT ask the candidate to "implement", "write code for", "write a function", or give any coding/algorithm task.
                - Do NOT repeat or rephrase any topic from the questions already asked above.
                - Prefer a mix of conceptual questions ("What is the difference between X and Y?"), experience-based questions ("Tell me about a time you..."), and scenario-based questions ("How would you approach...").
                - Base the question on the candidate's resume and the job description where relevant.
                - Do not provide the answer or any explanation.
                - Return ONLY the question text. No numbering, no quotes, no markdown.
                """
                .formatted(jobRole, jobDescription, resumeText, previousQuestionsBlock);
    }

    public String buildEvaluationPrompt(String question,
                                        String answer) {

        return """
                You are an experienced technical interviewer.

                Evaluate the candidate's answer.

                Question:
                %s

                Candidate Answer:
                %s

                Return ONLY valid JSON.

                {
                  "score": 0-10,
                  "feedback": "Short constructive feedback",
                  "nextQuestion": "Next technical interview question"
                }

                Do not return markdown.
                Do not return explanation.
                Do not wrap JSON inside ``` blocks.
                """
                .formatted(question, answer);
    }

    public String buildFollowupPrompt(String jobRole,
                                      String previousQuestion,
                                      String candidateAnswer,
                                      Double score) {

        return """
            You are an experienced technical interviewer conducting a real, spoken interview for a %s role.

            Previous Question:
            %s

            Candidate Answer:
            %s

            Score:
            %.1f / 10

            Rules:
            - If score is below 5, ask an easier question on a DIFFERENT topic.
            - If score is between 5 and 8, ask a question of similar difficulty on a DIFFERENT topic.
            - If score is above 8, increase the difficulty, still on a DIFFERENT topic.
            - The question must be short (1-2 sentences), natural, and conversational.
            - Do NOT ask the candidate to "implement", "write code for", or "write a function".
            - Do NOT repeat or rephrase the previous question's topic.

            Return ONLY the question text. No numbering, no quotes, no markdown, no explanation.
            """
                .formatted(
                        jobRole,
                        previousQuestion,
                        candidateAnswer,
                        score
                );
    }

    public String buildReportPrompt(String jobRole,
                                    String jobDescription,
                                    String resumeText,
                                    String interviewData) {

        return """
            You are a senior technical interviewer.

            Candidate Role:
            %s

            Job Description:
            %s

            Resume:
            %s

            Interview Transcript:
            %s

            Generate a final interview report.

            Return ONLY valid JSON.

            {
              "overallScore": 0,
              "strengths":"...",
              "weaknesses":"...",
              "recommendations":"..."
            }

            Do not use markdown.
            Do not wrap JSON inside ``` blocks.
            """
                .formatted(
                        jobRole,
                        jobDescription,
                        resumeText,
                        interviewData
                );
    }
}