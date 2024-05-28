package com.likelion.mini2team.controller;

import com.likelion.mini2team.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(Authentication authentication) {
        Long id = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUser(id));
    }
}
