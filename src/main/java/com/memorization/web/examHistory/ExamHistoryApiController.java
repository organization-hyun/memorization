package com.memorization.web.examHistory;

import com.memorization.service.exam.history.ExamHistoryService;
import com.memorization.web.dto.response.ExamHistoryResponseDto;
import com.memorization.web.examHistory.dto.ExamHistoriesDto;
import com.memorization.web.examHistory.dto.ExamHistorySaveDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam-histories")
public class ExamHistoryApiController {

    private final ExamHistoryService examHistoryService;

    @Operation(summary = "응시 이력 리스트 조회")
    @GetMapping
    public ExamHistoriesDto getExamHistories() {
        return examHistoryService.getExamHistories();
    }

    @Operation(summary = "응시 이력 조회")
    @GetMapping("/{examHistoryId}/items")
    public ExamHistoryResponseDto getExamHistory(@PathVariable Long examHistoryId) {
        return examHistoryService.getItems(examHistoryId);
    }

    @Operation(summary = "시험지 제출")
    @PostMapping
    public Long saveExamHistory(@RequestBody ExamHistorySaveDto dto) {
        return examHistoryService.saveExamHistory(dto);
    }

}
