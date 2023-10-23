package com.memorization.web.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MarkingResponseDto {

    List<Long> incorrectIdList = new ArrayList<>();
}
