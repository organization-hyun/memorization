package com.memorization.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TermUpdateRequestDto {

    String word;
    String description;
    List<String> keywords;
}
