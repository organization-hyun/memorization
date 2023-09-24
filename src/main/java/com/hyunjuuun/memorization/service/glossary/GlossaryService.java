package com.hyunjuuun.memorization.service.glossary;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Transactional
    public void delete(Long id) {
        glossaryRepository.deleteById(id);
    }

    @Transactional
    public Long update(Long id, String title) {
        Glossary glossary = glossaryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        glossary.updateTitle(title);
        return id;
    }

//    @Transactional
//    public Long update(GlossarySaveRequestDto requestDto) {
//        glossaryRepository.findBy()
//        return glossaryRepository.save(requestDto.toEntity()).getId();
//    }
}
