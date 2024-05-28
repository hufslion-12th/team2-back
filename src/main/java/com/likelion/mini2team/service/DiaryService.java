package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.diary.Diary;
import com.likelion.mini2team.domain.diary.DiaryCreateRequest;
import com.likelion.mini2team.domain.diary.DiaryCreateResponse;
import com.likelion.mini2team.domain.diary.DiaryReadResponse;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.repository.AlbumRepository;
import com.likelion.mini2team.repository.DiaryRepository;
import com.likelion.mini2team.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public DiaryCreateResponse createDiary(Long userIdx, DiaryCreateRequest dto) {
        Diary newDiary = new Diary();
        User selectedUser = userRepository.findById(userIdx)
                        .orElseThrow(() -> new RuntimeException("유저 못찾음"));
        Album selectedAlbum = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new RuntimeException("앨범이 존재하지 않습니다"));
        String newImage = dto.getImage().isEmpty() ? null : imageService.saveImage(dto.getImage());

        newDiary.setTitle(dto.getTitle());
        newDiary.setContent(dto.getContent());
        newDiary.setLocation(dto.getLocation());
        newDiary.setImage(newImage);
        newDiary.setOwner(selectedUser);
        newDiary.setDiaryTime(dto.getDiaryTime());

        Diary savedDiary = diaryRepository.save(newDiary);
        selectedAlbum.getDiaries().add(savedDiary);
        albumRepository.save(selectedAlbum);

        return new DiaryCreateResponse(savedDiary.getId(), savedDiary.getTitle());
    }

    public DiaryReadResponse getDiary(Long diaryId) {
        Diary selectedDiary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("Diary not found"));
        return new DiaryReadResponse(
                selectedDiary.getTitle(),
                selectedDiary.getContent(),
                selectedDiary.getLocation(),
                selectedDiary.getImage(),
                selectedDiary.getDiaryTime()
        );
    }

}
