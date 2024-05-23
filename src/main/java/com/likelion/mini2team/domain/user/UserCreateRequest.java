package com.likelion.mini2team.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserCreateRequest {
    private String userId;
    private String password;
    private String nickname;
}
