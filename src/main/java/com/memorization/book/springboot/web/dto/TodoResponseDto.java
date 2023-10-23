package com.memorization.book.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private List<ItemDto> items;

    public static TodoResponseDto of() {
        ItemDto item1 = new ItemDto(1L, "타이틀1");
        ItemDto item2 = new ItemDto(2L, "타이틀2");

        List<ItemDto> result = new ArrayList<>();
        result.add(item1);
        result.add(item2);

        return new TodoResponseDto(result);
    }
}
