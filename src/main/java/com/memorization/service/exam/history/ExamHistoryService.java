package com.memorization.service.exam.history;

import com.memorization.domain.exam.history.ExamHistoryRepository;
import com.memorization.web.dto.response.ExamHistoriesResponseDto;
import com.memorization.web.dto.response.ExamQuizHistoriesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExamHistoryService {

    private final ExamHistoryRepository examHistoryRepository;

    public ExamHistoriesResponseDto getExamHistories() {
        return ExamHistoriesResponseDto.of(examHistoryRepository.findAll());
    }

    public ExamQuizHistoriesResponseDto getExamQuizHistories(Long examHistoryId) {
        return ExamQuizHistoriesResponseDto.of(
                examHistoryRepository.findById(examHistoryId).orElseThrow(NoSuchElementException::new));
    }

    public Long deleteExamHistory(Long examHistoryId) {
        examHistoryRepository.deleteById(examHistoryId);
        return examHistoryId;
    }
}
