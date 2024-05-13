package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.diary.Diary;
import com.likelion.mini2team.domain.diary.DiaryCreateRequest;
import com.likelion.mini2team.domain.diary.DiaryCreateResponse;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.repository.DiaryRepository;
import com.likelion.mini2team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    public DiaryCreateResponse createDiary(DiaryCreateRequest dto) {
        Diary newDiary = new Diary();
        User selectedUser = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("유저 못찾음"));

        newDiary.setTitle(dto.getTitle());
        newDiary.setContent(dto.getContent());
        newDiary.setLocation(dto.getLocation());
        newDiary.setOwner(selectedUser);
        newDiary.setDiaryTime(dto.getDiaryTime());

        Diary savedDiary = diaryRepository.save(newDiary);

        return new DiaryCreateResponse(savedDiary.getId(), savedDiary.getTitle());
    }
}
