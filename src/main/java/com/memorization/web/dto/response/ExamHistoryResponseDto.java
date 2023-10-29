package com.memorization.web.dto.response;

import com.memorization.domain.examHistory.ExamHistory;
import com.memorization.web.dto.ExamHistoryItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamHistoryResponseDto {

    String title;
    List<ExamHistoryItemDto> examHistoryItems;

    public static ExamHistoryResponseDto of(ExamHistory examHistory) {
        return new ExamHistoryResponseDto(examHistory.getTitle(),
                examHistory.getExamHistoryItemList().stream()
                        .map(ExamHistoryItemDto::of)
                        .collect(Collectors.toList())
        );
    }
}
