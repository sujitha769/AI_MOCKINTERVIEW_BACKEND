package com.interview.aiservice.dto.groq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroqMessage {

    private String role;
    private String content;

}