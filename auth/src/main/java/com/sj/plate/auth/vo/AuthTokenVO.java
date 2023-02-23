package com.sj.plate.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenVO {
    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;
    private String accessName;
    private int authority;
}

