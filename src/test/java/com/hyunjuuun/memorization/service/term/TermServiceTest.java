package com.hyunjuuun.memorization.service.term;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import com.hyunjuuun.memorization.domain.glossary.GlossaryRepository;
import com.hyunjuuun.memorization.domain.term.Term;
import com.hyunjuuun.memorization.domain.term.TermRepository;
import com.hyunjuuun.memorization.web.dto.request.TermSaveRequestDto;
import com.hyunjuuun.memorization.web.dto.request.TermUpdateRequestDto;
import com.hyunjuuun.memorization.web.dto.response.TermsResponseDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TermServiceTest {

    @Autowired TermRepository termRepository;

    @Autowired GlossaryRepository glossaryRepository;

    @Autowired TermService termService;

    Glossary glossary;

    @BeforeEach
    void initGlossary() {
        Glossary glossary1 = Glossary.create("title1");
        glossaryRepository.save(glossary1);
        this.glossary = glossary1;
    }

    @Test
    void getTerms() {
        TermsResponseDto terms = termService.getTerms(3L);
        assertEquals(terms.getTerms().size(), 3);
    }
    
    @Test
    @Transactional
    void saveTerm() {
        //given

        //when
        Long termId = termService.saveTerm(new TermSaveRequestDto(glossary.getId(), "word1", "desc1"));

        //then
        assertEquals(termRepository.findById(termId).get().getWord(), "word1");
    }

    @Test
    @Transactional
    void updateTerm() {
        //given

        //when
        Long termId = termService.updateTerm(new TermUpdateRequestDto(6L, "updateWord1", "updateDesc1"));

        //then
        assertEquals(termRepository.findById(6L).get().getWord(), "updateWord1");
    }

}