package com.likelion.mini2team.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCreateResponse {
    private long dirayId;
    private String title;
}
