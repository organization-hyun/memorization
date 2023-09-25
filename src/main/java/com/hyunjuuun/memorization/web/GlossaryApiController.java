package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.glossary.GlossaryService;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import com.hyunjuuun.memorization.web.dto.GlossaryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GlossaryApiController {

    private final GlossaryService glossaryService;

    @GetMapping("/glossaries")
    public GlossariesResponseDto get() {
        return glossaryService.getGlossaries();
    }

    @PostMapping("/glossaries")
    public ResponseEntity<Long> save(@RequestBody GlossarySaveRequestDto requestDto) {
        return ResponseEntity.ok(glossaryService.saveGlossary(requestDto));
    }

    @DeleteMapping ("/glossaries/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        glossaryService.deleteGlossary(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/glossaries/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                       @RequestBody GlossaryUpdateRequestDto requestDto) {
        return ResponseEntity.ok(
                glossaryService.updateGlossary(id, requestDto.getTitle()));
    }
}
