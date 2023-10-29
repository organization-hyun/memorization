package com.memorization.service.quiz;

import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.enums.QuizType;
import com.memorization.web.dto.response.ExamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public ExamResponseDto getQuizzes(Long glossaryId) {
        List<Term> terms = termRepository.findByGlossaryId(glossaryId);
//        int termCount = terms.size();
//        List<QuizDto> quizDtoList = new ArrayList<>();
//
//        for (int i = 0; i < termCount; i++) {
//            Term termForQuiz = terms.remove(random.nextInt(terms.size()));// 문제 중복출제 방지
//
//            int quizTypeRandomIndex = random.nextInt(2);
//            QuizType quizType = QuizType.getByIdx(quizTypeRandomIndex);
//            quizDtoList.add(new QuizDto(termForQuiz.getId(), quizType, extractQuizText(termForQuiz, quizType)));
//        }

        return ExamResponseDto.of(terms);
    }


    private boolean removeTermNotExist(List<Long> termIdList, Long termId) {
        if (!termRepository.existsById(termId)) { // 존재하지 않는 Id로 퀴즈 요청했을 경우
            termIdList.remove(termId);
            return true;
        }
        return false;
    }

    private String extractQuizText(Term termForQuiz, QuizType quizType) {
        if (quizType.equals(QuizType.WORD)) {
            return termForQuiz.getWord();
        } else {
            return termForQuiz.getDescription();
        }
    }

}
