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
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "시험(exam)을 출제한다. exam은 여러개 quiz로 구성되어있다.")
    @GetMapping("/glossaries/{glossaryId}/exam")
    public ResponseEntity<QuizResponseDto> getQuizzes(@PathVariable Long glossaryId) {
        return ResponseEntity.ok(quizService.getQuizzes(glossaryId));
    }

    @Operation(summary = "퀴즈 답안지를 제출받아서 시험 이력(정답, 오답 등) 을 저장 한뒤, 시험 이력 키값을 반환한다.")
    @PostMapping("/glossaries/{glossaryId}/exam")
    public ResponseEntity<MarkingResponseDto> markAnswerSheet(@PathVariable Long glossaryId,
                                                              @RequestBody MarkingRequestDto markingRequestDto) {
        return ResponseEntity.ok(markingQuizService.markAnswerSheet(glossaryId, markingRequestDto));
    }
}
