package com.hyunjuuun.memorization.service.glossary;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.web.dto.request.GlossaryUpdateRequestDto;
import com.hyunjuuun.memorization.web.dto.response.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.request.GlossarySaveRequestDto;
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
    public void deleteGlossary(Long id) {
        glossaryRepository.deleteById(id);
    }

    @Transactional
    public Long updateGlossary(Long id, GlossaryUpdateRequestDto requestDto) {
        Glossary glossary = glossaryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        glossary.updateTitle(requestDto.getTitle());
        return id;
    }
}
