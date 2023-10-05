package com.hyunjuuun.memorization.web.dto.response;

import com.hyunjuuun.memorization.web.dto.TermDto;

import java.util.List;

public class MarkingResponseDto {

    Integer correct;
    Integer incorrect;
    List<TermDto> incorrectTerms;
}
