package com.hyunjuuun.memorization.domain.term;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Term(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public static Term create(String word, String description) {
        return new Term(word, description);
    }

    public void updateGlossary(Glossary glossary) {
        this.glossary = glossary;
    }
}
