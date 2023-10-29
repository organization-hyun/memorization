package com.memorization.domain.examHistory.item;

import com.memorization.domain.examHistory.ExamHistory;
import com.memorization.domain.term.Term;
import com.memorization.enums.QuizType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExamHistoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_history_id")
    private ExamHistory examHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;

    @Enumerated(EnumType.STRING)
    private QuizType quizType;

    // TODO 컬럼 자료형 우선 varchar로 처리
    private String userAnswer;

    private Boolean isCorrect;

    public ExamHistoryItem(ExamHistory examHistory, QuizType quizType, String question, String userAnswer, String answer, Boolean isCorrect) {
        this.examHistory = examHistory;
        this.quizType = quizType;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    private ExamHistoryItem(Term term, QuizType quizType, String userAnswer, Boolean isCorrect) {
        this.term = term;
        this.quizType = quizType;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    public static ExamHistoryItem create(Term term, QuizType quizType, String userAnswer) {
        return new ExamHistoryItem(term, quizType, userAnswer, term.checkUserAnswerIsCorrect(quizType, userAnswer));
    }

    public void setExamHistory(ExamHistory examHistory) {
        this.examHistory = examHistory;
    }

    public String getQuestion() {
        return this.term.getQuestion(this.quizType);
    }

}
