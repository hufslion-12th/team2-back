package com.likelion.mini2team.repository;

import com.likelion.mini2team.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
