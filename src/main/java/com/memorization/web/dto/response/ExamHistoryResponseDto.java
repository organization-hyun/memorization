package com.memorization.web.dto.response;

import com.memorization.domain.exam.history.ExamHistory;
import com.memorization.web.dto.QuizHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamHistoryResponseDto {

    String title;
    List<QuizHistoryDto> quizHistories;

    public static ExamHistoryResponseDto of(ExamHistory examHistory) {
        return new ExamHistoryResponseDto(examHistory.getTitle(),
                examHistory.getQuizHistoryList().stream().map(QuizHistoryDto::of).collect(Collectors.toList()));
    }
}
