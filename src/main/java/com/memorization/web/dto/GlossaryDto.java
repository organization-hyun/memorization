package com.memorization.web.dto;

import com.memorization.domain.glossary.Glossary;
import com.memorization.enums.GlossaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlossaryDto {

    private Long id;
    private String title;
    private GlossaryType glossaryType;

    public static GlossaryDto of(Glossary glossary) {
        return new GlossaryDto(glossary.getId(), glossary.getTitle(), glossary.getGlossaryType());
    }

}
