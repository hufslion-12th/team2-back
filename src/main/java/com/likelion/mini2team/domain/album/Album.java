package com.likelion.mini2team.domain.album;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.domain.diary.Diary;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;





import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; /*앨범이름 엔티티 정의*/

    @OneToOne
    private User owner;

    @OneToMany(mappedBy = "album")
    private List<Album> myAlbums;

    @OneToMany(mappedBy = "album")
    private List<Diary> myDiaries;
}
