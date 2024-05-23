package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.domain.user.UserCreateRequest;
import com.likelion.mini2team.domain.user.UserInfoResponse;
import com.likelion.mini2team.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public long createUser(UserCreateRequest dto){
        User newUser = new User();
        newUser.setUserId(dto.getUserId());
        newUser.setPassword(dto.getPassword());
        newUser.setNickname(dto.getNickname() != null ?
                dto.getNickname() : dto.getUserId());

        Album album = new Album();
        album.setName(dto.getNickname());
        album.setOwner(newUser);


        userRepository.save(newUser);
        return newUser.getId();
    }

    public UserInfoResponse getUser(long id){
        User selectedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( id + "가 발견되지 않았습니다."));
        return new UserInfoResponse(selectedUser.getUserId(), selectedUser.getNickname());
    }
}
