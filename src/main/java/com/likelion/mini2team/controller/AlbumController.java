package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album addAlbum(@RequestParam String name) {
        return albumService.addAlbum(name);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }

    @PutMapping("/{id}")
    public Album updateAlbumName(@PathVariable Long id, @RequestParam String newName) {
        return albumService.updateAlbumName(id, newName);
    }
}
