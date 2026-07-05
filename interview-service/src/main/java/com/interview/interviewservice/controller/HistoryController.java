package com.interview.interviewservice.controller;

import com.interview.interviewservice.dto.InterviewHistoryResponse;
import com.interview.interviewservice.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor

public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/history")
    public ResponseEntity<List<InterviewHistoryResponse>> getHistory() {

        return ResponseEntity.ok(
                historyService.getHistory()
        );

    }

}