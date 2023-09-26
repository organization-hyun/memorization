package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.term.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermApiController {

    private final TermService termService;

}
