package com.memorization.service.glossary;

import com.memorization.domain.glossary.Glossary;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.web.dto.request.GlossarySaveRequestDto;
import com.memorization.web.dto.request.GlossaryUpdateRequestDto;
import com.memorization.web.dto.response.GlossariesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GlossaryService {

    private final GlossaryRepository glossaryRepository;

    public GlossariesResponseDto getGlossaries() {
        return GlossariesResponseDto.of(glossaryRepository.findAll());
    }

    @Transactional
    public Long saveGlossary(GlossarySaveRequestDto requestDto) {
        return glossaryRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long deleteGlossary(Long id) {
        glossaryRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long updateGlossary(Long id, GlossaryUpdateRequestDto requestDto) {
        Glossary glossary = glossaryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        glossary.updateTitle(requestDto.getTitle());
        return id;
    }
}
