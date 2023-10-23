package com.memorization.web.dto.response;

import com.memorization.domain.term.Term;
import com.memorization.web.dto.TermDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TermsResponseDto {

    private List<TermDto> terms;

    public static TermsResponseDto of(List<Term> terms) {
        return new TermsResponseDto(
                terms.stream().map(TermDto::of).collect(Collectors.toList())
        );
    }
}
