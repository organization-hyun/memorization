package com.memorization.web.dto;

import com.memorization.domain.quiz.history.QuizHistory;
import com.memorization.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizHistoryDto {

    QuizType quizType;
    String question;
    String userAnswer;
    String answer;
    Boolean isCorrect;

    public static QuizHistoryDto of(QuizHistory quizHistory) {
        return new QuizHistoryDto(quizHistory.getQuizType(), quizHistory.getQuestion(),
                quizHistory.getUserAnswer(), quizHistory.getAnswer(), quizHistory.getIsCorrect());
    }
}
