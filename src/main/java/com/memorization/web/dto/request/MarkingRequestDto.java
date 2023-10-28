package com.memorization.web.dto.request;

import com.memorization.web.dto.MarkingDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MarkingRequestDto {
    List<MarkingDto> terms;
}

