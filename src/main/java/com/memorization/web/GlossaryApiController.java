package com.memorization.web;

import com.memorization.service.glossary.GlossaryService;
import com.memorization.service.quiz.MarkingQuizService;
import com.memorization.service.quiz.QuizService;
import com.memorization.service.term.TermService;
import com.memorization.web.dto.request.GlossarySaveRequestDto;
import com.memorization.web.dto.request.GlossaryUpdateRequestDto;
import com.memorization.web.dto.request.MarkingRequestDto;
import com.memorization.web.dto.request.TermSaveRequestDto;
import com.memorization.web.dto.response.GlossariesResponseDto;
import com.memorization.web.dto.response.MarkingResponseDto;
import com.memorization.web.dto.response.QuizResponseDto;
import com.memorization.web.dto.response.TermsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GlossaryApiController {

    private final GlossaryService glossaryService;
    private final TermService termService;
    private final QuizService quizService;
    private final MarkingQuizService markingQuizService;

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

    @GetMapping("/glossaries/{glossaryId}/quiz")
    public ResponseEntity<QuizResponseDto> getQuiz(@PathVariable Long glossaryId) {
        return ResponseEntity.ok(quizService.getQuizzes(glossaryId));
    }

    @PostMapping("/glossaries/{glossaryId}/quiz")
    public ResponseEntity<MarkingResponseDto> markAnswerSheet(@PathVariable Long glossaryId,
                                                              @RequestBody MarkingRequestDto markingRequestDto) {
        return ResponseEntity.ok(markingQuizService.markAnswerSheet(markingRequestDto));
    }
}
