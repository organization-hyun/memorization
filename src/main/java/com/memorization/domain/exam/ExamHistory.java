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

    // TODO: 10/27/23
    // String title 이어붙여서
            //  originalGlossaryTitle: glossary 명 추가 (기존용어집 타이틀)
            //  응시일자 examDate
            //  postfixNumber

    // TODO: 10/27/23 삭제
//    @OneToMany(mappedBy = "examHistory")
//    private List<ExamHistoryTerm> terms;

    @OneToMany(mappedBy = "examHistory")
    private List<Quiz> quizzes;

    // TODO: 10/27/23 OneToMany Quiz 엔티티 참조
}
