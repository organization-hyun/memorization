package com.memorization.web.dto;

import com.memorization.domain.glossary.Glossary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlossaryDto {

    private Long id;
    private String title;

    public static GlossaryDto of(Glossary glossary) {
        return new GlossaryDto(glossary.getId(), glossary.getTitle());
    }

}
