package com.memorization.web.dto.response;

import com.memorization.web.dto.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ExamResponseDto {

    Integer total;
    List<QuizDto> quizzes;

}
