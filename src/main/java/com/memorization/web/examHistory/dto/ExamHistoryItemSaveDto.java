package com.memorization.web.examHistory.dto;

import com.memorization.enums.QuizType;
import lombok.Getter;

@Getter
public class ExamHistoryItemSaveDto {

    private Long termId;
    private QuizType quizType;
    private String userAnswer;

}
