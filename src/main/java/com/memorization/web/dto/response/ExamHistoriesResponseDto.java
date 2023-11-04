package com.memorization.web.dto.response;

import com.memorization.domain.exam.history.ExamHistory;
import com.memorization.web.dto.ExamHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamHistoriesResponseDto {

    List<ExamHistoryDto> examHistories;

    public static ExamHistoriesResponseDto of(List<ExamHistory> examHistories) {
        return new ExamHistoriesResponseDto(
                examHistories.stream().map(ExamHistoryDto::of).collect(Collectors.toList())
        );
    }
}
