package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.album.AlbumContentsResponse;
import com.likelion.mini2team.domain.album.AlbumCreateRequest;
import com.likelion.mini2team.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public long addAlbum(Authentication authentication, @RequestBody AlbumCreateRequest dto) {
        Long userIdx = (Long) authentication.getPrincipal();
        return albumService.addAlbum(userIdx, dto);
    }

    @GetMapping
    public ResponseEntity<AlbumContentsResponse> getMyRootAlbumContents(Authentication authentication) {
        Long id = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(albumService.getMyAlbum(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumContentsResponse> getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbum(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }

    /***
     * 당장 사용하지 않는 기능이므로 일시적으로 비활성화했습니다.
    @PutMapping("/{id}")
    public Album updateAlbumName(@PathVariable Long id, @RequestParam String newName) {
        return albumService.updateAlbumName(id, newName);
    }
    ***/
}
