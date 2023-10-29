package com.memorization.service.exam.history;

import com.memorization.domain.examHistory.ExamHistory;
import com.memorization.domain.examHistory.ExamHistoryRepository;
import com.memorization.domain.examHistory.item.ExamHistoryItem;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.web.dto.response.ExamHistoryResponseDto;
import com.memorization.web.examHistory.dto.ExamHistoriesDto;
import com.memorization.web.examHistory.dto.ExamHistorySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamHistoryService {

    private final TermRepository termRepository;
    private final ExamHistoryRepository examHistoryRepository;

    public ExamHistoriesDto getExamHistories() {
        return ExamHistoriesDto.of(examHistoryRepository.findAll());
    }

    public ExamHistoryResponseDto getItems(Long examHistoryId) {
        return ExamHistoryResponseDto.of(
                examHistoryRepository.findById(examHistoryId).orElseThrow(NoSuchElementException::new));
    }

    @Transactional
    public Long saveExamHistory(ExamHistorySaveDto dto) {

        List<ExamHistoryItem> examHistoryItems = dto.getExamHistoryItems().stream()
                .map(item -> {
                    Term term = termRepository.findById(item.getTermId()).get();
                    return ExamHistoryItem.create(term, item.getQuizType(), item.getUserAnswer());
                })
                .collect(Collectors.toList());

        return examHistoryRepository.save(new ExamHistory("응시 이력", examHistoryItems)).getId();

    }

}
