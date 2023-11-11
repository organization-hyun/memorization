package com.memorization.domain.glossary;

import com.memorization.domain.BaseTimeEntity;
import com.memorization.domain.term.Term;
import com.memorization.enums.GlossaryType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Glossary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "glossary", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Term> terms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'BASIC'")
    private GlossaryType glossaryType = GlossaryType.BASIC;

    public static Glossary create(String title) {
        return new Glossary(title);
    }

    private Glossary(String title) {
        this.title = title;
    }

    public static Glossary create(String title, GlossaryType glossaryType) {
        return new Glossary(title, glossaryType);
    }

    private Glossary(String title, GlossaryType glossaryType) {
        this.title = title;
        this.glossaryType = glossaryType;
    }

    /**
     * 엔티티 특화 메소드
     */
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
