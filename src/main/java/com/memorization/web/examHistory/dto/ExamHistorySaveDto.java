package com.memorization.web.examHistory.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExamHistorySaveDto {

    private List<ExamHistoryItemSaveDto> examHistoryItems;

}
