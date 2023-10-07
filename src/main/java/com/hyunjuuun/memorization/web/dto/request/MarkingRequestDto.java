package com.hyunjuuun.memorization.web.dto.request;

import com.hyunjuuun.memorization.web.dto.MarkingDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MarkingRequestDto {
    List<MarkingDto> markingTerms;
}
