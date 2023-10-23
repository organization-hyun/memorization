package com.memorization.domain.exam;

import com.memorization.domain.term.Term;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExamHistoryTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_history_id")
    private ExamHistory examHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term incorrectTerm;

    public ExamHistoryTerm(ExamHistory examHistory, Term incorrectTerm) {
        this.examHistory = examHistory;
        this.incorrectTerm = incorrectTerm;
    }
}
