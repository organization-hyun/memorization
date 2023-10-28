package com.memorization.service.exam.history;

import com.memorization.domain.exam.history.ExamHistoryRepository;
import com.memorization.web.dto.response.ExamHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExamHistoryService {

    private final ExamHistoryRepository examHistoryRepository;

    public ExamHistoryResponseDto getExamHistory(Long examHistoryId) {
        return ExamHistoryResponseDto.of(
                examHistoryRepository.findById(examHistoryId).orElseThrow(NoSuchElementException::new));
    }
}
