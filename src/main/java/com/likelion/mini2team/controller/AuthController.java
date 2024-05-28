package com.likelion.mini2team.controller;

import com.likelion.mini2team.domain.auth.TokenDto;
import com.likelion.mini2team.domain.auth.UserJoinRequest;
import com.likelion.mini2team.domain.auth.UserLoginRequest;
import com.likelion.mini2team.service.AuthService;
import com.likelion.mini2team.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<String> createUser(@RequestBody UserJoinRequest dto) {
        authService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("유저가 정상적으로 생성되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequest dto) {
        return ResponseEntity.ok(authService.loginUser(dto));
    }


}
