package com.hyunjuuun.memorization.web.dto;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import lombok.Getter;

@Getter
public class GlossariesResponseDto {

    private Long id;
    private String title;

    public GlossariesResponseDto(Glossary glossary) {
        this.id = glossary.getId();
        this.title = glossary.getTitle();
    }

}
