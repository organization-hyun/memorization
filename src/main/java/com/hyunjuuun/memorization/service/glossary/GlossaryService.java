package com.hyunjuuun.memorization.service.glossary;

import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GlossaryService {

    private final GlossaryRepository glossaryRepository;

    public GlossariesResponseDto getGlossaries() {
        return GlossariesResponseDto.of(glossaryRepository.findAll());
    }

    @Transactional
    public Long save(GlossarySaveRequestDto requestDto) {
        return glossaryRepository.save(requestDto.toEntity()).getId();
    }

}
