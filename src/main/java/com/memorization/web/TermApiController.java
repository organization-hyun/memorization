package com.memorization.web;

import com.memorization.service.term.TermService;
import com.memorization.web.dto.request.TermUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TermApiController {

    private final TermService termService;

    @PutMapping("/terms/{id}")
    public ResponseEntity<?> updateTerm(@PathVariable Long id,
                                        @RequestBody TermUpdateRequestDto termUpdateRequestDto) {

        return ResponseEntity.ok(termService.updateTerm(id, termUpdateRequestDto));
    }

    @DeleteMapping("/terms/{ids}")
    public ResponseEntity<?> deleteTerm(@PathVariable List<Long> ids) {
        termService.deleteTerms(ids);
        return ResponseEntity.ok().build();
    }

}
