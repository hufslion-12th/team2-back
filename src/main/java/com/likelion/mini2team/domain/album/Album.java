package com.likelion.mini2team.domain.album;

import com.likelion.mini2team.domain.diary.Diary;
import com.likelion.mini2team.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; /* 앨범 이름 */

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> subAlbums;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries;

    private LocalDateTime createdAt = LocalDateTime.now();
}
