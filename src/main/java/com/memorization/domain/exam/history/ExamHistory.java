package com.memorization.domain.exam.history;

import com.memorization.domain.BaseTimeEntity;
import com.memorization.domain.quiz.history.QuizHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ExamHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "examHistory",cascade = CascadeType.ALL)
    private List<QuizHistory> quizHistoryList;

    public ExamHistory(String title) {
        this.title = title;
    }
}
