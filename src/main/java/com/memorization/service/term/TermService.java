package com.memorization.service.term;

import com.memorization.domain.glossary.Glossary;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.web.dto.request.TermSaveRequestDto;
import com.memorization.web.dto.request.TermUpdateRequestDto;
import com.memorization.web.dto.response.TermsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;
    private final GlossaryRepository glossaryRepository;

    public TermsResponseDto getTerms(Long glossaryId) {
        return TermsResponseDto.of(termRepository.findByGlossaryId(glossaryId));
    }

    @Transactional
    public Long saveTerm(Long glossaryId, TermSaveRequestDto termSaveRequestDto) {
        Term term = termSaveRequestDto.toEntity();
        Glossary glossary = glossaryRepository.getById(glossaryId);// Proxy조회, SpringBoot 2.5 < 에서 getReferencdById
        term.updateGlossary(glossary);
        return termRepository.save(term).getId();
    }

    @Transactional
    public Long updateTerm(Long id, TermUpdateRequestDto termUpdateRequestDto) {
        Term term = termRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        term.updateWord(termUpdateRequestDto.getWord());
        term.updateDescription(termUpdateRequestDto.getDescription());
        term.resetKeywords();
        term.updateKeywords(termUpdateRequestDto.getKeywords());
        return term.getId();
    }

    @Transactional
    public void deleteTerms(List<Long> ids) {
        termRepository.deleteAllById(ids);
    }
}
