package com.likelion.mini2team.repository;

import com.likelion.mini2team.domain.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    void deleteById(Integer id);
}
