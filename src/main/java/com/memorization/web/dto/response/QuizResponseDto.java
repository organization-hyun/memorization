package com.memorization.web.dto.response;

import com.memorization.web.dto.QuizDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuizResponseDto {

    Integer total;
    List<QuizDto> quizzes = new ArrayList<>();

    public QuizResponseDto(Integer totalQuizNumber) {
        this.total = totalQuizNumber;
    }
}
