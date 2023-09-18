package com.hyunjuuun.memorization.domain.term;

import com.hyunjuuun.memorization.domain.glossary.Glossary;

import javax.persistence.*;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Glossary glossary;

    private String word;

    private String description;

}
