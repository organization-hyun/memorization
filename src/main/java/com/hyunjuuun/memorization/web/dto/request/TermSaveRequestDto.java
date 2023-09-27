package com.hyunjuuun.memorization.web.dto.request;

import com.hyunjuuun.memorization.domain.term.Term;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TermSaveRequestDto {

    private String word;
    private String description;
    private List<String> keywords;

    public Term toEntity() {
        return Term.create(word, description, keywords);
    }
}
