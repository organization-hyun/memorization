package com.hyunjuuun.memorization.web.dto;

import com.hyunjuuun.memorization.domain.term.Term;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermDto {

    private Long id;
    private String word;
    private String description;

    public static TermDto of(Term term) {
        return new TermDto(term.getId(), term.getWord(), term.getDescription());
    }

}
