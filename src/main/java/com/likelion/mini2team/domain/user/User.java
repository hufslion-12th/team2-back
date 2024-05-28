package com.likelion.mini2team.domain.user;

import com.likelion.mini2team.domain.album.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter @Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;
    private String password;
    private String nickname;

    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Album rootAlbum;

    private LocalDateTime createdAt = LocalDateTime.now();

}
