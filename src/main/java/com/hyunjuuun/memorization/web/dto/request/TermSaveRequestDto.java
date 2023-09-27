package com.hyunjuuun.memorization.web.dto.request;

import com.hyunjuuun.memorization.domain.term.Term;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermSaveRequestDto {

    private String word;
    private String description;

    public Term toEntity() {
        return Term.create(word, description);
    }
}
