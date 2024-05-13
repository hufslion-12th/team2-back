package com.likelion.mini2team.domain.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class DiaryCreateRequest {
    private String title;
    private String content;
    private String location;
    private long userId;
    private LocalDateTime diaryTime;
}
