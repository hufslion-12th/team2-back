package com.likelion.mini2team.domain.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class DiaryCreateRequest {
    private long albumId;
    private String title;
    private String content;
    private String location;
    private MultipartFile image;
    private LocalDateTime diaryTime;
}
