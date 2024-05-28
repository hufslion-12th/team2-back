package com.likelion.mini2team.service;

import com.likelion.mini2team.domain.album.Album;
import com.likelion.mini2team.domain.auth.TokenDto;
import com.likelion.mini2team.domain.auth.UserJoinRequest;
import com.likelion.mini2team.domain.auth.UserLoginRequest;
import com.likelion.mini2team.domain.user.User;
import com.likelion.mini2team.repository.AlbumRepository;
import com.likelion.mini2team.repository.UserRepository;
import com.likelion.mini2team.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void createUser(UserJoinRequest dto){
        if (userRepository.findByUserId(dto.getUserId()).isPresent()){
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }

        User newUser = new User();
        newUser.setUserId(dto.getUserId());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setNickname(dto.getNickname() != null ?
                dto.getNickname() : dto.getUserId());

        Album album = new Album();
        album.setName(dto.getNickname());
        album.setOwner(newUser);
        newUser.setRootAlbum(album);
        userRepository.save(newUser);
        albumRepository.save(album);

    }

    public TokenDto loginUser(UserLoginRequest dto){
        User selectedUser = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.getPassword(), selectedUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        TokenDto newToken = jwtTokenProvider.createToken(selectedUser.getId());
        selectedUser.setRefreshToken(newToken.getRefreshToken());
        userRepository.save(selectedUser);

        return newToken;
    }

}
