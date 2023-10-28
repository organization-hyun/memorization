package com.memorization.domain.quiz;

import com.memorization.domain.exam.ExamHistory;
import com.memorization.enums.QuizType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExamHistory examHistory;

    @Enumerated(EnumType.STRING)
    private QuizType quizType;

    private String question;

    private String userAnswer;

    private String answer;

    private Boolean isCorrect;

    public Quiz(ExamHistory examHistory, QuizType quizType, String question, String userAnswer, String answer, Boolean isCorrect) {
        this.examHistory = examHistory;
        this.quizType = quizType;
        this.question = question;
        this.userAnswer = userAnswer;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
