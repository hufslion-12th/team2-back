package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.diary.DiaryCreateRequest;
import com.likelion.mini2team.domain.diary.DiaryCreateResponse;
import com.likelion.mini2team.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public DiaryCreateResponse createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest){
        return diaryService.createDiary(diaryCreateRequest);
    }

    // 구현되지 않은 Service 이므로 삭제하였음
//    @GetMapping("/diaries/delete")
//    public String diaryDelete(Integer id) {
//
//        diaryService.diaryDelete(id);
//
//        return "redirect:/diaries";
//    }
}
