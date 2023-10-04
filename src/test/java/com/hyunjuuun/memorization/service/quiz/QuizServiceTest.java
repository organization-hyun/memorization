package com.hyunjuuun.memorization.service.quiz;

import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.web.dto.QuizDto;
import com.hyunjuuun.memorization.web.dto.response.QuizResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class QuizServiceTest {

    @Autowired TermRepository termRepository;
    @Autowired QuizService quizService;

    @Test
    void getQuizzes() {

        QuizResponseDto quizResponseDto = quizService.getQuizzes(1L);

        assertThat(quizResponseDto.getTotal()).isEqualTo(2);
        List<QuizDto> quizzes = quizResponseDto.getQuizzes();
        for (QuizDto quiz : quizzes) {
            log.info("type={}, quizText={}", quiz.getType(), quiz.getQuizText());
        }
    }
}