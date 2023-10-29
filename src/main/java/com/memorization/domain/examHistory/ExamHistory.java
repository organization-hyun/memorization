package com.memorization.domain.examHistory;

import com.memorization.domain.BaseTimeEntity;
import com.memorization.domain.examHistory.item.ExamHistoryItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ExamHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "examHistory", cascade = CascadeType.ALL)
    private List<ExamHistoryItem> examHistoryItemList = new ArrayList<>();

    public ExamHistory(String title) {
        this.title = title;
    }

    public ExamHistory(String title, List<ExamHistoryItem> examHistoryItems) {
        this.title = title;
        this.examHistoryItemList.addAll(examHistoryItems);
        examHistoryItems.stream()
                .forEach(item -> item.setExamHistory(this));
    }

}
