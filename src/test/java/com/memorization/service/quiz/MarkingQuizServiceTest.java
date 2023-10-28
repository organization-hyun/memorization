package com.memorization.service.quiz;

import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MarkingQuizServiceTest {

    @Autowired MarkingQuizService markingQuizService;
    @Autowired
    TermRepository termRepository;

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

        //then
    }
}