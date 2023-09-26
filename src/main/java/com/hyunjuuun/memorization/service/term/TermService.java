package com.hyunjuuun.memorization.service.term;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.domain.term.Term;
import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.web.dto.request.TermSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;
    private final GlossaryRepository glossaryRepository;

    @Transactional
    public Long saveTerm(TermSaveRequestDto termSaveRequestDto) {
        Term term = termSaveRequestDto.toEntity();
        Glossary glossary = glossaryRepository.getById(termSaveRequestDto.getGlossaryId());// Proxy조회, SpringBoot 2.5 < 에서 getReferencdById
        term.updateGlossary(glossary);
        return termRepository.save(term).getId();
    }
}
