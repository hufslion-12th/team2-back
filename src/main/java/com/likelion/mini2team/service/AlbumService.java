package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.album.AlbumCreateRequest;
import com.likelion.mini2team.domain.album.AlbumContentsResponse;
import com.likelion.mini2team.domain.album.AlbumResponse;
import com.likelion.mini2team.domain.diary.DiaryInfoResponse;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.repository.AlbumRepository;
import com.likelion.mini2team.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자를 직접 만드셨는데, 이 어노테이션으로 한 번에 해결하였습니다.
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    /* 앨범 추가*/
    // TODO : 해당 앨범 id가 실제 User의 앨범인지 확인하는 검증 필요 - User의 Injection 공격 대응
    public long addAlbum(long userIdx, AlbumCreateRequest dto) {
        Album selectedAlbum = albumRepository.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Album not found"));
        User selectedUser = userRepository.findById(userIdx)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        Album newAlbum = new Album();
        newAlbum.setName(dto.getName());
        newAlbum.setOwner(selectedUser);

        selectedAlbum.getSubAlbums().add(newAlbum);
        return albumRepository.save(newAlbum).getId();
    }

    /* 앨범 조회*/

    /***
     User 기반으로 조회하면 서브 앨범 구분없이 모든 앨범이 다 나올 우려가 있어서 제외했습니다.
    public List<Album> getAlbumsByUserId(Long userId) {
        // 사용자 ID를 기반으로 해당 사용자의 앨범을 조회
        return albumRepository.findByOwnerId(userId);
    }
     ***/

    public AlbumContentsResponse getAlbum(long id) {
        Album selectedAlbum =  albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("앨범이 없습니다."));
        return new AlbumContentsResponse(
                selectedAlbum.getSubAlbums().stream().map(album -> new AlbumResponse(
                        album.getId(),
                        album.getName()
                )).toList(),
                selectedAlbum.getDiaries().stream().map(diary -> new DiaryInfoResponse(diary.getId(), diary.getTitle())).toList(),
                selectedAlbum.getId(),
                selectedAlbum.getName()
        );
    }

    public AlbumContentsResponse getMyAlbum(long userIndex) {
        User user = userRepository.findById(userIndex)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return this.getAlbum(user.getRootAlbum().getId());
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
