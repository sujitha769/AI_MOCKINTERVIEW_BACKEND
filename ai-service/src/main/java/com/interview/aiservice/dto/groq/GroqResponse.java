package com.interview.aiservice.dto.groq;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroqResponse {

    private List<Choice> choices;

}