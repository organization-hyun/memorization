package com.memorization.web.dto;

import com.memorization.enums.QuizType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class MarkingDto {

    Long id;
    QuizType quizType;
    String userAnswer;
}
