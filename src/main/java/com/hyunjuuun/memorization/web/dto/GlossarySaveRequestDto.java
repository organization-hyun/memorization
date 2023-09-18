package com.hyunjuuun.memorization.web.dto;

import com.hyunjuuun.memorization.domain.glossary.Glossary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GlossarySaveRequestDto {

    private String title;

    @Builder
    public GlossarySaveRequestDto(String title) {
        this.title = title;
    }

    public Glossary toEntity() {
        return Glossary.builder()
                .title(title)
                .build();
    }

}
