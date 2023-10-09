package com.hyunjuuun.memorization.web.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomQuizRequestDto {
    List<Long> termIdList;
}
