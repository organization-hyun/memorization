package com.memorization.web.examHistory.dto;

import com.memorization.domain.examHistory.ExamHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamHistoryDto {

    private Long id;
    private String title;

    public static ExamHistoryDto of(ExamHistory examHistory) {
        return new ExamHistoryDto(examHistory.getId(), examHistory.getTitle());
    }

}
