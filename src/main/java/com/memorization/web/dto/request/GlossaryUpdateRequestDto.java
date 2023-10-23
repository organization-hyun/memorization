package com.memorization.web.dto.request;

import com.memorization.domain.glossary.Glossary;
import lombok.Getter;

@Getter
public class GlossaryUpdateRequestDto {

    private String title;

    public Glossary toEntity() {
        return Glossary.create(title);
    }

}
