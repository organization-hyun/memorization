package com.hyunjuuun.memorization.web.dto.request;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import lombok.Getter;

@Getter
public class GlossaryUpdateRequestDto {

    private String title;

    public Glossary toEntity() {
        return Glossary.create(title);
    }

}
