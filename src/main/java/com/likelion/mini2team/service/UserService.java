package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.auth.UserJoinRequest;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.domain.user.UserInfoResponse;
import com.likelion.mini2team.domain.auth.UserLoginRequest;
import com.likelion.mini2team.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserInfoResponse getUser(long id){
        User selectedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( id + "가 발견되지 않았습니다."));
        return new UserInfoResponse(selectedUser.getUserId(), selectedUser.getNickname());
    }
}
