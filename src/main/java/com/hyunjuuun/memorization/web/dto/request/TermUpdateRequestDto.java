package com.hyunjuuun.memorization.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TermUpdateRequestDto {

    String word;
    String description;
}
