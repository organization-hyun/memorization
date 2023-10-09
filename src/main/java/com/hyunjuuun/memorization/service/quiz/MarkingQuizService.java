package com.hyunjuuun.memorization.service.quiz;

import com.hyunjuuun.memorization.domain.exam.ExamHistory;
import com.hyunjuuun.memorization.domain.exam.ExamHistoryRepository;
import com.hyunjuuun.memorization.domain.exam.ExamHistoryTerm;
import com.hyunjuuun.memorization.domain.exam.ExamHistoryTermRepository;
import com.hyunjuuun.memorization.domain.term.Term;
import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.enums.QuizType;
import com.hyunjuuun.memorization.web.dto.MarkingDto;
import com.hyunjuuun.memorization.web.dto.request.MarkingRequestDto;
import com.hyunjuuun.memorization.web.dto.response.MarkingResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Quiz를 채점한다
 */

@Service
@AllArgsConstructor
public class MarkingQuizService {

    private final TermRepository termRepository;
    private final ExamHistoryRepository examHistoryRepository;
    private final ExamHistoryTermRepository examHistoryTermRepository;

    public MarkingResponseDto markAnswerSheet(MarkingRequestDto markingRequestDto) {
        List<MarkingDto> markingTerms = markingRequestDto.getMarkingTerms();
        MarkingResponseDto markingResponseDto = new MarkingResponseDto();
        for (MarkingDto markingTerm : markingTerms) {
            if(isCorrectAnswer(markingTerm)) continue;
            markingResponseDto.getIncorrectIdList().add(markingTerm.getId());
            saveHistory(markingTerm);
        }
        return markingResponseDto;
    }

    private void saveHistory(MarkingDto markingTerm) {
        ExamHistory examHistory = new ExamHistory();
        ExamHistoryTerm examHistoryTerm = new ExamHistoryTerm(examHistory, termRepository.getById(markingTerm.getId()));
        examHistoryRepository.save(examHistory);
        examHistoryTermRepository.save(examHistoryTerm);
    }

    private boolean isCorrectAnswer(MarkingDto markingTerm) {
        String quizType = markingTerm.getType();

        if (quizType.equals(QuizType.WORD.name())) {
            return checkDescription(markingTerm.getId(), markingTerm.getAnswer());
        } else if (quizType.equals(QuizType.DESCRIPTION.name())) {
            return checkWord(markingTerm.getId(), markingTerm.getAnswer());
        } else {
            throw new RuntimeException("Wrong quiz type");
        }
    }

    private boolean checkWord(Long id, String word) {
        return termRepository.findById(id)
                .orElseThrow(NoSuchElementException::new)
                .getWord().equals(word);
    }

    private boolean checkDescription(Long id, String description) {
        Term term = termRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<String> keywords = term.getKeywords();
        for (String keyword : keywords) {
            if(!description.contains(keyword)) return false;
        }
        return true;
    }
}
