package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.album.MyContentsResponse;
import com.likelion.mini2team.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /* 앨범 추가*/
    public Album addAlbum(String name) {
        Album album = new Album();
        album.setName(name);
        return albumRepository.save(album);
    }

    /* 앨범 조회*/

    // User 기반으로 조회하면 서브 앨범 구분없이 모든 앨범이 다 나올 우려가 있어서 제외했습니다.
//    public List<Album> getAlbumsByUserId(Long userId) {
//        // 사용자 ID를 기반으로 해당 사용자의 앨범을 조회
//        return albumRepository.findByOwnerId(userId);
//    }

    public MyContentsResponse getAlbum(long id) {
        Album selectedAlbum =  albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("앨범이 없습니다."));
        return new MyContentsResponse(selectedAlbum.getSubAlbums(), selectedAlbum.getDiaries());
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
