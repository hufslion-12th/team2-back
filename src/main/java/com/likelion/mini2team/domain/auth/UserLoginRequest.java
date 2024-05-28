package com.likelion.mini2team.domain.auth;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userId;
    private String password;
}
