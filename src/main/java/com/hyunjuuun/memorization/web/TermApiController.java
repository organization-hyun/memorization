package com.hyunjuuun.memorization.web;

import com.hyunjuuun.memorization.service.term.TermService;
import com.hyunjuuun.memorization.web.dto.request.TermUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TermApiController {

    private final TermService termService;


    @PutMapping("/terms/{id}")
    public ResponseEntity<?> updateTerm(@PathVariable Long id,
                                        @RequestBody TermUpdateRequestDto termUpdateRequestDto) {

        return ResponseEntity.ok(termService.updateTerm(id, termUpdateRequestDto));
    }

    @DeleteMapping("/terms/{id}")
    public ResponseEntity<?> deleteTerm(@PathVariable Long id) {
        termService.deleteTerm(id);
        return ResponseEntity.ok().build();
    }

}
