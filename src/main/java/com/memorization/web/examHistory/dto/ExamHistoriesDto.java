package com.memorization.web.examHistory.dto;

import com.memorization.domain.examHistory.ExamHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamHistoriesDto {

    private List<ExamHistoryDto> examHistories;

    public static ExamHistoriesDto of(List<ExamHistory> examHistories) {
        return new ExamHistoriesDto(
                examHistories.stream()
                        .map(ExamHistoryDto::of)
                        .collect(Collectors.toList())
        );
    }
}
