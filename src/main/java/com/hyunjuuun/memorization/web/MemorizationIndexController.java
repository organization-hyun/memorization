package com.hyunjuuun.memorization.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MemorizationIndexController {

    @GetMapping("/glossaries/save")
    public String glossariesSave() {
        return "glossaries-save";
    }

}
