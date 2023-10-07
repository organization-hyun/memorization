package com.hyunjuuun.memorization.enums;

import lombok.Getter;

@Getter
public enum QuizType {
    WORD(0), DESCRIPTION(1);

    Integer idx;

    QuizType(int idx) {
        this.idx = idx;
    }

    public Integer idx() {
        return this.idx;
    }
}
