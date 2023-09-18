package com.hyunjuuun.memorization.domain.glossary;

import com.hyunjuuun.memorization.domain.BaseTimeEntity;
import com.hyunjuuun.memorization.domain.term.Term;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Glossary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "glossary")
    private List<Term> terms = new ArrayList();

    @Builder
    public Glossary(String title) {
        this.title = title;
    }

}
