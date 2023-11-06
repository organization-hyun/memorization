package com.memorization.web;

import com.memorization.service.exam.history.ExamHistoryService;
import com.memorization.web.dto.response.ExamHistoriesResponseDto;
import com.memorization.web.dto.response.ExamQuizHistoriesResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExamHistoryApiController {

    private final ExamHistoryService examHistoryService;

    @Operation(summary = "사용자가 응시했던 시험 결과 id와 title을 반환한다")
    @GetMapping("/exam/histories")
    public ResponseEntity<ExamHistoriesResponseDto> getExamHistories() {
        return ResponseEntity.ok(examHistoryService.getExamHistories());
    }

    @Operation(summary = "사용자가 응시했던 시험 결과를 반환한다")
    @GetMapping("/exam/histories/{examHistoryId}")
    public ResponseEntity<ExamQuizHistoriesResponseDto> getExamHistory(@PathVariable Long examHistoryId) {
        return ResponseEntity.ok(examHistoryService.getExamQuizHistories(examHistoryId));
    }

    @Operation(summary = "사용자가 응시했던 시험 결과 이력을 삭제한다")
    @DeleteMapping("/exam/histories/{examHistoryId}")
    public ResponseEntity<Long> deleteExamHistory(@PathVariable Long examHistoryId) {
        return ResponseEntity.ok(examHistoryService.deleteExamHistory(examHistoryId));
    }
}
