package com.memorization.service.quiz;

import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.enums.QuizType;
import com.memorization.web.dto.QuizDto;
import com.memorization.web.dto.request.CustomQuizRequestDto;
import com.memorization.web.dto.response.CustomQuizResponseDto;
import com.memorization.web.dto.response.QuizResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * glossaryId를 이용해 문제를 출제한다
 */

// TODO: 10/9/23 문제 출제 수 관련 로직 - 유효성검증, 요청한 수 대로 출제
@Service
@RequiredArgsConstructor
public class QuizService {

    private final TermRepository termRepository;
    private static final int QUIZ_NUMBER = 2;

    public QuizResponseDto getQuizzes(Long glossaryId) {
        List<Term> terms = termRepository.findByGlossaryId(glossaryId);
        QuizResponseDto quizResponseDto = new QuizResponseDto(QUIZ_NUMBER);

        Random random = new Random();
        for (int i = 0; i < QUIZ_NUMBER; i++) {
            int size = terms.size();
            int termRandomIndex = random.nextInt(size);
            Term termForQuiz = terms.get(termRandomIndex);

            terms.remove(termRandomIndex); // 문제 중복출제 방지

            int typeRandomIndex = random.nextInt(2);
            String typeForQuiz = QuizType.getNameByIdx(typeRandomIndex);
            quizResponseDto.getQuizzes().add(new QuizDto(termForQuiz.getId(), typeForQuiz, extractQuizText(termForQuiz, typeRandomIndex)));
        }

        return quizResponseDto;
    }

    public CustomQuizResponseDto getCustomQuizzes(CustomQuizRequestDto customQuizRequestDto) {
        List<Long> termIdList = customQuizRequestDto.getTermIdList();
        CustomQuizResponseDto customQuizResponseDto = new CustomQuizResponseDto(QUIZ_NUMBER);

        Random random = new Random();
        for (int i = 0; i < QUIZ_NUMBER; i++) {
            int size = termIdList.size();
            int termRandomIndex = random.nextInt(size);
            Long termId = termIdList.get(termRandomIndex);
            Term termForQuiz = termRepository.findById(termId).orElseThrow(NoSuchElementException::new);

            int typeRandomIndex = random.nextInt(2);
            String typeForQuiz = QuizType.getNameByIdx(typeRandomIndex);
            customQuizResponseDto.getCustomQuizzes().add(new QuizDto(termForQuiz.getId(), typeForQuiz, extractQuizText(termForQuiz, typeRandomIndex)));
        }

        return customQuizResponseDto;
    }

    private boolean removeTermNotExist(List<Long> termIdList, Long termId) {
        if(!termRepository.existsById(termId)) { // 존재하지 않는 Id로 퀴즈 요청했을 경우
            termIdList.remove(termId);
            return true;
        }
        return false;
    }

    private String extractQuizText(Term termForQuiz, int typeIndex) {
        String quizText;
        if (typeIndex == 0) {
            quizText = termForQuiz.getWord();
        } else {
            quizText = termForQuiz.getDescription();
        }
        return quizText;
    }

}
