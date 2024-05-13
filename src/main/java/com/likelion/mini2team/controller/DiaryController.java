package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.diary.DiaryCreateRequest;
import com.likelion.mini2team.domain.diary.DiaryCreateResponse;
import com.likelion.mini2team.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public DiaryCreateResponse createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest){
        return diaryService.createDiary(diaryCreateRequest);
    }
}
