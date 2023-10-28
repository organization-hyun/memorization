package com.memorization.service.quiz;

import com.memorization.domain.exam.history.ExamHistory;
import com.memorization.domain.exam.history.ExamHistoryRepository;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.domain.quiz.history.QuizHistory;
import com.memorization.domain.quiz.history.QuizHistoryRepository;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.enums.QuizType;
import com.memorization.web.dto.MarkingDto;
import com.memorization.web.dto.request.MarkingRequestDto;
import com.memorization.web.dto.response.MarkingResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Quiz를 채점한다
 */

@Service
@AllArgsConstructor
public class MarkingQuizService {

    private final GlossaryRepository glossaryRepository;
    private final TermRepository termRepository;
    private final ExamHistoryRepository examHistoryRepository;
    private final QuizHistoryRepository quizHistoryRepository;

    public MarkingResponseDto markAnswerSheet(Long glossaryId, MarkingRequestDto markingRequestDto) {
        List<MarkingDto> answerSheet = markingRequestDto.getAnswerSheet();

        ExamHistory examHistory = new ExamHistory(glossaryRepository.findById(glossaryId).orElseThrow(NoSuchElementException::new)
                .getTitle() + "_" + LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
        examHistoryRepository.save(examHistory);

        for (MarkingDto markingDto : answerSheet) {
            QuizType quizType = markingDto.getQuizType();
            Term answerTerm = termRepository.findById(markingDto.getTermId()).orElseThrow(NoSuchElementException::new);
            String answer;
            String question;
            if (quizType.equals(QuizType.WORD)) {
                question = answerTerm.getWord();
                answer = answerTerm.getDescription();
            } else {
                question = answerTerm.getDescription();
                answer = answerTerm.getWord();
            }
            QuizHistory quiz = new QuizHistory(examHistory, quizType, question, markingDto.getUserAnswer(), answer,
                    isCorrectAnswer(quizType, answerTerm, markingDto.getUserAnswer()));
            quizHistoryRepository.save(quiz);
        }

        return new MarkingResponseDto(examHistory.getId());
    }

    // TODO: 10/27/23 glossary 자동 생성

    private boolean isCorrectAnswer(QuizType quizType, Term term, String userAnswer) {

        if (quizType.equals(QuizType.WORD)) {
            return checkDescription(term.getKeywords(), userAnswer);
        } else if (quizType.equals(QuizType.DESCRIPTION)) {
            return checkWord(term.getWord(), userAnswer);
        } else {
            throw new RuntimeException("Wrong quiz type");
        }
    }

    private boolean checkWord(String answerWord, String userWord) {
        return userWord.replaceAll("\\([^)]*\\)|\\s", "").equals(answerWord);
    }

    private boolean checkDescription(List<String> keywords, String userDescription) {
        for (String keyword : keywords) {
            if(!userDescription.contains(keyword)) return false;
        }
        return true;
    }
}
