package com.likelion.mini2team.domain.diary;

import com.likelion.mini2team.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String location;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private LocalDateTime diaryTime;
    private LocalDateTime createdAt = LocalDateTime.now();

}
