package com.memorization.domain.term;

import com.memorization.domain.glossary.Glossary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public void updateKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void resetKeywords() {
        keywords.clear();
    }
}
