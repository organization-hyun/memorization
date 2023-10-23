package com.memorization.service.quiz;

import com.memorization.domain.glossary.Glossary;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.web.dto.QuizDto;
import com.memorization.web.dto.request.CustomQuizRequestDto;
import com.memorization.web.dto.response.CustomQuizResponseDto;
import com.memorization.web.dto.response.QuizResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class QuizServiceTest {

    @Autowired
    GlossaryRepository glossaryRepository;
    @Autowired
    TermRepository termRepository;
    @Autowired QuizService quizService;

    @Test
    void getQuizzes() {

        //given
        Term springTerm = Term.create("spring",
                "Java 기반의 애플리케이션 프레임워크이다. POJO, AOP, DI, IoC 등의 특징을 가진다");

        Term javaTerm = Term.create("spring",
                "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 가장 큰 특징은 플랫폼에 독립적인 언어이다.");

        Glossary glossary = glossaryRepository.getById(1L);
        springTerm.updateGlossary(glossary);
        javaTerm.updateGlossary(glossary);

        termRepository.save(springTerm);
        termRepository.save(javaTerm);

        //when
        QuizResponseDto quizResponseDto = quizService.getQuizzes(1L);

        //then
        assertThat(quizResponseDto.getTotal()).isEqualTo(2);
        List<QuizDto> quizzes = quizResponseDto.getQuizzes();
        for (QuizDto quiz : quizzes) {
            log.info("id={}, type={}, quizText={}", quiz.getId(), quiz.getType(), quiz.getQuizText());
        }
    }

    @Test
    void getCustomQuizzes() {
        //given
        int termIdBoundary = (int) termRepository.count();
        Random random = new Random();
        final int SAMPLE_REQUEST_TERM_NUMBER = 3;
        List<Long> termIdList = new ArrayList<>();
        for (int i = 0; i < SAMPLE_REQUEST_TERM_NUMBER; i++) {
            termIdList.add((long) (random.nextInt(termIdBoundary) + 1));
        }
        CustomQuizRequestDto customQuizRequestDto = CustomQuizRequestDto.builder().termIdList(termIdList).build();

        //when
        CustomQuizResponseDto customQuizzes = quizService.getCustomQuizzes(customQuizRequestDto);

        //then
        assertThat(customQuizzes.getTotal()).isEqualTo(2);
        List<QuizDto> quizzes = customQuizzes.getCustomQuizzes();
        for (QuizDto quiz : quizzes) {
            log.info("id={}, type={}, quizText={}", quiz.getId(), quiz.getType(), quiz.getQuizText());
        }
    }
}