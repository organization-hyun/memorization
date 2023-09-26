package com.hyunjuuun.memorization.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermUpdateRequestDto {

    Long id;
    String word;
    String description;
}
