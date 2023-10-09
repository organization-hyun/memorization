package com.hyunjuuun.memorization.service.quiz;

import com.hyunjuuun.memorization.domain.term.Term;
import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.enums.QuizType;
import com.hyunjuuun.memorization.web.dto.MarkingDto;
import com.hyunjuuun.memorization.web.dto.request.MarkingRequestDto;
import com.hyunjuuun.memorization.web.dto.response.MarkingResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MarkingQuizServiceTest {

    @Autowired MarkingQuizService markingQuizService;
    @Autowired TermRepository termRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void markAnswerSheet() {
        //given
        List<String> springKeywords = new ArrayList<>();
        springKeywords.add("Java");
        springKeywords.add("프레임워크");
        springKeywords.add("POJO");
        Term springTerm = Term.create("spring",
                "Java 기반의 애플리케이션 프레임워크이다. POJO, AOP, DI, IoC 등의 특징을 가진다",
                springKeywords);
        termRepository.save(springTerm);

        List<String> javaKeywords = new ArrayList<>();
        javaKeywords.add("썬 마이크로시스템즈");
        javaKeywords.add("객체 지향 프로그래밍 언어");
        javaKeywords.add("플랫폼에 독립적");
        Term javaTerm = Term.create("java",
                "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 가장 큰 특징은 플랫폼에 독립적인 언어이다.",
                javaKeywords);
        termRepository.save(javaTerm);

        //when
        MarkingDto springMarkingTerm = MarkingDto.builder().id(springTerm.getId()).type(QuizType.WORD.name()).answer("Java, 프레임워크, POJO").build();
        MarkingDto javaMarkingTerm = MarkingDto.builder().id(springTerm.getId()).type(QuizType.DESCRIPTION.name()).answer("오답").build();
        List<MarkingDto> answers = new ArrayList<>();
        answers.add(springMarkingTerm);
        answers.add(javaMarkingTerm);
        MarkingRequestDto markingRequestDto = MarkingRequestDto.builder().markingTerms(answers).build();
        MarkingResponseDto markingResponseDto = markingQuizService.markAnswerSheet(markingRequestDto);

        //then
        assertThat(markingResponseDto.getIncorrectIdList().size()).isEqualTo(1);
        assertThat(markingResponseDto.getIncorrectIdList().get(0)).isEqualTo(javaMarkingTerm.getId());
    }
}