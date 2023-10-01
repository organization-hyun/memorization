package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.glossary.GlossaryService;
import com.hyunjuuun.memorization.service.term.TermService;
import com.hyunjuuun.memorization.web.dto.request.GlossarySaveRequestDto;
import com.hyunjuuun.memorization.web.dto.request.GlossaryUpdateRequestDto;
import com.hyunjuuun.memorization.web.dto.request.TermSaveRequestDto;
import com.hyunjuuun.memorization.web.dto.request.TermUpdateRequestDto;
import com.hyunjuuun.memorization.web.dto.response.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.response.TermsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GlossaryApiController {

    private final GlossaryService glossaryService;
    private final TermService termService;

    @GetMapping("/glossaries")
    public GlossariesResponseDto get() {
        return glossaryService.getGlossaries();
    }

    @PostMapping("/glossaries")
    public ResponseEntity<Long> save(@RequestBody GlossarySaveRequestDto requestDto) {
        return ResponseEntity.ok(glossaryService.saveGlossary(requestDto));
    }

    @DeleteMapping("/glossaries/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(glossaryService.deleteGlossary(id));
    }

    @PutMapping("/glossaries/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody GlossaryUpdateRequestDto requestDto) {
        return ResponseEntity.ok(
                glossaryService.updateGlossary(id, requestDto));
    }

    @GetMapping("/glossaries/{glossaryId}/terms")
    public ResponseEntity<TermsResponseDto> getTerms(@PathVariable Long glossaryId) {
        return ResponseEntity.ok(termService.getTerms(glossaryId));
    }

    @PostMapping("/glossaries/{glossaryId}/terms")
    public ResponseEntity<?> saveTerm(@PathVariable Long glossaryId,
                                      @RequestBody TermSaveRequestDto termSaveRequestDto) {
        return ResponseEntity.ok(termService.saveTerm(glossaryId, termSaveRequestDto));
    }

    @PutMapping("/glossaries/{glossaryId}/terms/{id}")
    public ResponseEntity<?> updateTerm(@PathVariable Long glossaryId,
                                        @PathVariable Long id,
                                        @RequestBody TermUpdateRequestDto termUpdateRequestDto) {
        return ResponseEntity.ok(termService.updateTerm(id, termUpdateRequestDto));
    }

    @DeleteMapping("/glossaries/{glossaryId}/terms/{id}")
    public ResponseEntity<?> deleteTerm(@PathVariable Long glossaryId,
                                        @PathVariable Long id) {
        termService.deleteTerm(id);
        return ResponseEntity.ok().build();
    }

}
