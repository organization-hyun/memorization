package com.memorization.web.dto;

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
    String quizText; // TODO: 10/27/23 question 으로 변경
}
