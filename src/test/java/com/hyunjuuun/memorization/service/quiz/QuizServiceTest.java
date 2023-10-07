package com.hyunjuuun.memorization.service.quiz;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.domain.term.Term;
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

    @Autowired GlossaryRepository glossaryRepository;
    @Autowired TermRepository termRepository;
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

        QuizResponseDto quizResponseDto = quizService.getQuizzes(1L);

        assertThat(quizResponseDto.getTotal()).isEqualTo(2);
        List<QuizDto> quizzes = quizResponseDto.getQuizzes();
        for (QuizDto quiz : quizzes) {
            log.info("id={}, type={}, quizText={}", quiz.getId(), quiz.getType(), quiz.getQuizText());
        }
    }
}