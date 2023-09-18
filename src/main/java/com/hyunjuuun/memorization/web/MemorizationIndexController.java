package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.glossary.GlossaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MemorizationIndexController {

    private final GlossaryService glossaryService;

    @GetMapping("/glossaries")
    public String index(Model model) {
        model.addAttribute("glossaries", glossaryService.findAllDesc());
        return "memorization-index";
    }

    @GetMapping("/glossaries/save")
    public String glossariesSave() {
        return "glossaries-save";
    }

}
