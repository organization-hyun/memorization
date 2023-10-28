package com.memorization.web.dto;

import com.memorization.enums.QuizType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarkingDto {

    Long id;
    QuizType quizType;
    String userAnswer;
}
