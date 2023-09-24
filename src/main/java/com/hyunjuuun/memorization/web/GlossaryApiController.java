package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.glossary.GlossaryService;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import lombok.RequiredArgsConstructor;
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
    public Long save(@RequestBody GlossarySaveRequestDto requestDto) {
        return glossaryService.save(requestDto);
    }

    @DeleteMapping ("/glossaries")
    public void delete(@PathVariable Long id) {
        glossaryService.delete(id);
    }

//    @PutMapping("/glossaries/{id}")
//    public Long update(@PathVariable Long id) {
//        return glossaryService.save(requestDto);
//    }

}
