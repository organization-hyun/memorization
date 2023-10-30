package com.memorization.domain.term;

import com.memorization.domain.glossary.Glossary;
import com.memorization.enums.QuizType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.memorization.enums.QuizType.DESCRIPTION;
import static com.memorization.enums.QuizType.WORD;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Glossary glossary;

    private String word;

    private String description;

    @ElementCollection
    @CollectionTable(name = "keywords", joinColumns = @JoinColumn(name = "term_id"))
    @Column(name = "keyword")
    private List<String> keywords = new ArrayList<>();

    private Term(String word, String description) {
        this.word = word;
        this.description = description;
    }

    private Term(String word, String description, List<String> keywords) {
        this.word = word;
        this.description = description;
        this.keywords = new ArrayList<>(keywords);
    }

    public void updateGlossary(Glossary glossary) {
        this.glossary = glossary;
    }

    /**
     * 엔티티 메소드
     */
    public static Term create(String word, String description) {
        return new Term(word, description);
    }

    public static Term create(String word, String description, List<String> keywords) {
        return new Term(word, description, keywords);
    }

    public void updateWord(String word) {
        this.word = word;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void addKeywords(List<String> keywords) {
        keywords.addAll(keywords);
    }

    public void resetKeywords() {
        keywords.clear();
    }

    // TODO 우선 무조건 맞다고 처리
    public boolean checkUserAnswerIsCorrect(QuizType quizType, String userAnswer) {

        if (quizType == WORD) {
            return true;
        }

        if (quizType == DESCRIPTION) {
            return this.word.equals(
                    Objects.isNull(userAnswer) ? "" : userAnswer.replaceAll(" ", ""));
        }

        throw new IllegalStateException();
    }

    public String getQuestion(QuizType quizType) {

        if (quizType == WORD) {
            return word;
        }

        if (quizType == DESCRIPTION) {
            return description;
        }

        throw new IllegalStateException();
    }
}
