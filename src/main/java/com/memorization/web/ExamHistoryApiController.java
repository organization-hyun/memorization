package com.memorization.web;

import com.memorization.service.exam.history.ExamHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExamHistoryApiController {

    private final ExamHistoryService examHistoryService;

    @Operation(summary = "사용자가 응시했던 시험 결과를 반환한다. History 는 복수형이 없으므로 복수형은 HistoryList 로 반환한다")
    @GetMapping("/exam/history/{examHistoryId}")
    public ResponseEntity getExamHistory(@PathVariable Long examHistoryId) {
        return ResponseEntity.ok(examHistoryService.getExamHistory(examHistoryId));
    }
}
