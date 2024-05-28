package com.likelion.mini2team.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DiaryInfoResponse {
    private Long id;
    private String title;
}
