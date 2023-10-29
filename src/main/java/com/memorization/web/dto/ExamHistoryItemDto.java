package com.memorization.web.dto;

import com.memorization.domain.examHistory.item.ExamHistoryItem;
import com.memorization.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamHistoryItemDto {

    QuizType quizType;
    String question;
    String userAnswer;
    Boolean isCorrect;

    public static ExamHistoryItemDto of(ExamHistoryItem examHistoryItem) {
        return new ExamHistoryItemDto(examHistoryItem.getQuizType(), examHistoryItem.getQuestion(),
                examHistoryItem.getUserAnswer(), examHistoryItem.getIsCorrect());
    }

}
