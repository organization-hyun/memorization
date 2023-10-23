package com.memorization.service.term;

import com.memorization.domain.glossary.Glossary;
import com.memorization.domain.glossary.GlossaryRepository;
import com.memorization.domain.term.Term;
import com.memorization.domain.term.TermRepository;
import com.memorization.web.dto.request.TermSaveRequestDto;
import com.memorization.web.dto.request.TermUpdateRequestDto;
import com.memorization.web.dto.response.TermsResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TermServiceTest {

    @Autowired TermRepository termRepository;

    @Autowired
    GlossaryRepository glossaryRepository;

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
    @Rollback(value = false)
    void saveTerm() {
        //given
        List<String> keywords = new ArrayList<>();
        keywords.add("keyword1");
        keywords.add("keyword1");
        keywords.add("keyword1");

        //when
        Long termId = termService.saveTerm(glossary.getId(),
                new TermSaveRequestDto("word1", "desc1", keywords));

        //then
        Term term = termRepository.findById(termId).get();
        assertEquals(term.getWord(), "word1");
        Assertions.assertEquals(term.getGlossary().getId(), this.glossary.getId());

    }

    @Test
    @Transactional
    void updateTerm() {
        //given

        //when
        Long termId = termService.updateTerm(6L,
                new TermUpdateRequestDto("updateWord1", "updateDesc1", new ArrayList<>(Arrays.asList("updateKeyword1", "updateKeyword2"))));

        //then
        assertEquals(termRepository.findById(6L).get().getWord(), "updateWord1");
    }

}