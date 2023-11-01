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
    QuizType quizType;
    String question;
}
