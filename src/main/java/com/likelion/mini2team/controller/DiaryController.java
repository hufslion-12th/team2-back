package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.diary.DiaryCreateRequest;
import com.likelion.mini2team.domain.diary.DiaryCreateResponse;
import com.likelion.mini2team.domain.diary.DiaryReadResponse;
import com.likelion.mini2team.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public ResponseEntity<DiaryCreateResponse> createDiary(Authentication authentication, @ModelAttribute DiaryCreateRequest diaryCreateRequest){
        Long userIdx = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(diaryService.createDiary(userIdx, diaryCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryReadResponse> getDiary(@PathVariable Long id){
        return ResponseEntity.ok(diaryService.getDiary(id));
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
