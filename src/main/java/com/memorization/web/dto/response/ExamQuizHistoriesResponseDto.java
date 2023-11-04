package com.memorization.web.dto.response;

import com.memorization.domain.exam.history.ExamHistory;
import com.memorization.web.dto.QuizHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamQuizHistoriesResponseDto {

    List<QuizHistoryDto> quizHistories;

    public static ExamQuizHistoriesResponseDto of(ExamHistory examHistory) {
        return new ExamQuizHistoriesResponseDto(
                examHistory.getQuizHistoryList().stream().map(QuizHistoryDto::of).collect(Collectors.toList()));
    }
}
