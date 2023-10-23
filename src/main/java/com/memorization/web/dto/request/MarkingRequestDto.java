package com.memorization.web.dto.request;

import com.memorization.web.dto.MarkingDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MarkingRequestDto {
    List<MarkingDto> markingTerms;
}
