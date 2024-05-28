package com.likelion.mini2team.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequest {
    private String userId;
    private String password;
    private String nickname;
}
