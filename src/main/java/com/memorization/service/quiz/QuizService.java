package com.memorization.service.quiz;

import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.enums.ExamType;
import com.memorization.enums.QuizType;
import com.memorization.web.dto.QuizDto;
import com.memorization.web.dto.response.ExamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * glossaryId를 이용해 문제를 출제한다
 */

@Service
@RequiredArgsConstructor
public class QuizService {

    private final TermRepository termRepository;
    private Random random = new Random();

    public ExamResponseDto getExam(Long glossaryId, ExamType examType) {
        List<Term> terms = termRepository.findByGlossaryId(glossaryId);
        int termCount = terms.size();
        List<QuizDto> quizDtoList = new ArrayList<>();

        for (int i = 0; i < termCount; i++) {
            Term termForQuiz = terms.remove(random.nextInt(terms.size()));// 문제 중복출제 방지
            QuizType quizType = getQuizTypeByExamType(examType);
            quizDtoList.add(new QuizDto(termForQuiz.getId(), quizType, extractQuestion(termForQuiz, quizType)));
        }

        return new ExamResponseDto(quizDtoList.size(), quizDtoList);
    }

    private QuizType getQuizTypeByExamType(ExamType examType) {
        if (examType.equals(ExamType.WORD)) {
            return QuizType.WORD;

        } else if (examType.equals(ExamType.DESCRIPTION)) {
            return QuizType.DESCRIPTION;
        }
        return QuizType.getByIdx(random.nextInt(2));
    }

    private String extractQuestion(Term termForQuiz, QuizType quizType) {
        if (quizType.equals(QuizType.WORD)) {
            return termForQuiz.getWord();
        } else {
            return termForQuiz.getDescription();
        }
    }

}
