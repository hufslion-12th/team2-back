package com.likelion.mini2team.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DiaryReadResponse {
    private String title;
    private String content;
    private String location;
    private String image;
    private LocalDateTime diaryTime;
}
