package com.interview.interviewservice.controller;

import com.interview.interviewservice.dto.ReportResponse;
import com.interview.interviewservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor

public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{interviewId}")
    public ResponseEntity<ReportResponse> generateReport(
            @PathVariable Long interviewId) {

        return ResponseEntity.ok(
                reportService.generateReport(interviewId)
        );
    }

}