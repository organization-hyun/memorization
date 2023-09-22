package com.hyunjuuun.memorization.web.dto;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import lombok.Getter;

@Getter
public class GlossarySaveRequestDto {

    private String title;

    public Glossary toEntity() {
        return Glossary.create(title);
    }

}
