package com.memorization.service.quiz;

import com.memorization.domain.exam.history.ExamHistory;
import com.memorization.domain.exam.history.ExamHistoryRepository;
import com.memorization.domain.glossary.Glossary;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.domain.quiz.history.QuizHistory;
import com.memorization.domain.quiz.history.QuizHistoryRepository;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.enums.QuizType;
import com.memorization.utils.TimeUtil;
import com.memorization.web.dto.MarkingDto;
import com.memorization.web.dto.request.MarkingRequestDto;
import com.memorization.web.dto.response.MarkingResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

// TODO: 11/4/23 리팩토링!!
@Service
@AllArgsConstructor
@Transactional
public class MarkingQuizService {

    private final GlossaryRepository glossaryRepository;
    private final TermRepository termRepository;
    private final ExamHistoryRepository examHistoryRepository;
    private final QuizHistoryRepository quizHistoryRepository;

    public MarkingResponseDto markAnswerSheet(Long glossaryId, MarkingRequestDto markingRequestDto) {
        List<MarkingDto> answerSheet = markingRequestDto.getAnswerSheet();

        ExamHistory examHistory = new ExamHistory(glossaryRepository.findById(glossaryId).orElseThrow(NoSuchElementException::new)
                .getTitle() + "_" + TimeUtil.getNowDateMinute());
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

        makeCorrections(glossaryId, markingRequestDto);

        return new MarkingResponseDto(examHistory.getId());
    }

    private boolean isCorrectAnswer(QuizType quizType, Term term, String userAnswer) {

        if (quizType.equals(QuizType.WORD)) {
            return checkDescription(term, userAnswer);
        } else if (quizType.equals(QuizType.DESCRIPTION)) {
            return checkWord(term.getWord(), userAnswer);
        } else {
            throw new RuntimeException("Wrong quiz type");
        }
    }

    private boolean checkWord(String answerWord, String userWord) {
        if(!StringUtils.hasText(userWord)) return false;
        return userWord.replaceAll("\\([^)]*\\)|\\s", "").equals(answerWord.replaceAll("\\s",""));
    }

    private boolean checkDescription(Term term, String userDescription) {
        if(!StringUtils.hasText(userDescription)) return false;

        List<String> keywords = term.getKeywords();
        userDescription = userDescription.replaceAll("\\s", "");
        if(keywords.size() == 0) {
            String answerDescription = term.getDescription().replaceAll("\\s", "");
            if(answerDescription.equals(userDescription)) return true;
            else return false;
        }

        for (String keyword : keywords) {
            boolean isCorrect = false;
            String[] keywordElements = keyword.split("\\|\\|");
            for (String keywordElement : keywordElements) {
                keywordElement = keywordElement.replaceAll("\\s", "");
                if(userDescription.contains(keywordElement)) {
                    isCorrect = true;
                    break;
                }
            }

            if(!isCorrect) return false;
        }
        return true;
    }

    // 오답노트를 만들다
    private void makeCorrections(Long glossaryId, MarkingRequestDto markingRequestDto) {

        String title = glossaryRepository.findById(glossaryId).orElseThrow(NoSuchElementException::new).getTitle() + "_" + TimeUtil.getNowDateMinute()+"_오답노트";
        Glossary newGlossary = Glossary.create(title);
        int wrongAnswerCount = 0;
        List<MarkingDto> answerSheet = markingRequestDto.getAnswerSheet();
        for (MarkingDto markingDto : answerSheet) {
            Term term = termRepository.findById(markingDto.getTermId()).orElseThrow(NoSuchElementException::new);
            if (!isCorrectAnswer(markingDto.getQuizType(), term, markingDto.getUserAnswer())) {
                Term newTerm = Term.create(term.getWord(), term.getDescription(), term.getKeywords());
                newTerm.updateGlossary(newGlossary);
                termRepository.save(newTerm);
                ++wrongAnswerCount;
            }
        }

        if(wrongAnswerCount > 0) glossaryRepository.save(newGlossary);
    }
}
