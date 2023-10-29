package com.memorization.web.examHistory;

import com.memorization.service.exam.history.ExamHistoryService;
import com.memorization.web.examHistory.dto.ExamHistorySaveDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam-history")
public class ExamHistoryApiController {

    private final ExamHistoryService examHistoryService;

    @Operation(summary = "시험지 제출")
    @PostMapping
    public Long saveExamHistory(@RequestBody ExamHistorySaveDto dto) {
        return examHistoryService.saveExamHistory(dto);
    }

    @Operation(summary = "사용자가 응시했던 시험 결과를 반환한다. History 는 복수형이 없으므로 복수형은 HistoryList 로 반환한다")
    @GetMapping("/exam/history/{examHistoryId}")
    public ResponseEntity getExamHistory(@PathVariable Long examHistoryId) {
        return ResponseEntity.ok(examHistoryService.getExamHistory(examHistoryId));
    }
}
