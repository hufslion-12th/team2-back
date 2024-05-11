package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    /* 앨범 추가*/
    public Album addAlbum(String name) {
        Album album = new Album();
        album.setName(name);
        return albumRepository.save(album);
    }

    /* 앨범 조회*/
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    /* 앨범 삭제 */
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    /* 앨범 수정 */
    public Album updateAlbumName(Long id, String newName) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();
            album.setName(newName);
            return albumRepository.save(album);
        } else {
            throw new IllegalArgumentException("해당 ID의 앨범을 찾을 수 없습니다.");
        }
    }
}
