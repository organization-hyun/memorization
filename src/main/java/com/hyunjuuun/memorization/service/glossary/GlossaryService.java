package com.hyunjuuun.memorization.service.glossary;

import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GlossaryService {

    private final GlossaryRepository glossaryRepository;

    @Transactional
    public Long save(GlossarySaveRequestDto requestDto) {
        return glossaryRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<GlossariesResponseDto> findAllDesc() {
        return glossaryRepository.findAllDesc().stream()
                .map(GlossariesResponseDto::new)
                .collect(Collectors.toList());
    }

}
