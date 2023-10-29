package com.memorization.web.dto;

import com.memorization.domain.term.Term;
import com.memorization.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuizDto {

    Long id;
    QuizType type;
    String question;

    public static QuizDto of(Term term) {
        return new QuizDto(term.getId(), QuizType.DESCRIPTION, term.getDescription());
    }

}
