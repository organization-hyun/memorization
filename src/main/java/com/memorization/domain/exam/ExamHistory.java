package com.memorization.domain.exam;

import com.memorization.domain.BaseTimeEntity;
import com.memorization.domain.quiz.Quiz;
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

    @OneToMany(mappedBy = "examHistory")
    private List<Quiz> quizzes;

    public ExamHistory(String title) {
        this.title = title;
    }
}
