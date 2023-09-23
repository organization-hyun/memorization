package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.glossary.GlossaryService;
import com.hyunjuuun.memorization.web.dto.GlossariesResponseDto;
import com.hyunjuuun.memorization.web.dto.GlossarySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
