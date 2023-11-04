package com.memorization.web.dto;

import com.memorization.domain.exam.history.ExamHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamHistoryDto {
    Long id;
    String title;

    public static ExamHistoryDto of(ExamHistory examHistory) {
        return new ExamHistoryDto(examHistory.getId(), examHistory.getTitle());
    }
}
