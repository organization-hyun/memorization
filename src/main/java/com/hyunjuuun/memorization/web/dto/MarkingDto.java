package com.hyunjuuun.memorization.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarkingDto {

    Long id;
    String type;
    String answer;
}
