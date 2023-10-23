package com.memorization.web.dto.response;

import com.memorization.web.dto.QuizDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomQuizResponseDto {

    Integer total;
    List<QuizDto> customQuizzes = new ArrayList<>();

    public CustomQuizResponseDto(Integer totalQuizNumber) {
        this.total = totalQuizNumber;
    }
}
