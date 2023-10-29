package com.memorization.web.dto.response;

import com.memorization.domain.term.Term;
import com.memorization.web.dto.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ExamResponseDto {

    List<QuizDto> quizzes;

    public static ExamResponseDto of(List<Term> terms) {
        return new ExamResponseDto(
                terms.stream()
                        .map(QuizDto::of)
                        .collect(Collectors.toList()));
    }

}
