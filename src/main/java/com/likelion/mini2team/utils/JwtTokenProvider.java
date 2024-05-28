package com.likelion.mini2team.utils;

import com.likelion.mini2team.domain.auth.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public TokenDto createToken(Long id){
        long expAccess = (1000*60*60)*3L;
        long expRefresh = (1000*60*60)*24*3L;

        String accessToken = createTokenByType(id, true, expAccess);
        String refreshToken = createTokenByType(id, false, expRefresh);

        return new TokenDto(accessToken, refreshToken);
    }

    private String createTokenByType(Long id, boolean isAccessToken, long time){
        Date now = new Date();
        Date validity = new Date(now.getTime() + time);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",id);
        if (isAccessToken) {
            claims.put("role","user");
        }
        claims.put("type", isAccessToken ? "access" : "refresh");
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public Long extractUser(String token){
        Claims claims = extractClaims(token);
        return claims.get("id", Long.class);
    }

    public boolean isExpired(String token){
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                    .getPayload().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            return true;
        }
    }

    public TokenDto renewAccessToken(String refreshToken) {
        if (!isExpired(refreshToken)) {
            Claims claims = extractClaims(refreshToken);
            if ("refresh".equals(claims.get("type"))) {
                return createToken(claims.get("id", Long.class));
            }
        }
        throw new SecurityException("Invalid refresh token");
    }
}
