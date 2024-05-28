package com.likelion.mini2team.domain.album;

import com.likelion.mini2team.domain.diary.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AlbumResponse {
    Long albumId;
    String albumName;
}
