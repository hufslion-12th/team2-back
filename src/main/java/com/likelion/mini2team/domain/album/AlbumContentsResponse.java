package com.likelion.mini2team.domain.album;

import com.likelion.mini2team.domain.diary.Diary;
import com.likelion.mini2team.domain.diary.DiaryInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class AlbumContentsResponse {
    List<AlbumResponse> albums;
    List<DiaryInfoResponse> diaries;
    Long albumId;
    String albumName;
}
