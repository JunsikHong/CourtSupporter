package com.court.supporter.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.court.supporter.security.DefaultUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {
   
   private static final Long ACCESS_TOKEN_VALIDATION_SECOND = 60L * 60 * 1000; // 한시간
    private static final Long REFRESH_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 30 * 1000; // 한 달
    private static final String BEARER_TYPE = "bearer";
    
    private final Key key;
    
    //jwt 발급
    public JwtToken createJwtToken(DefaultUserDetails defaultUserDetails) {
        Claims claims = getClaims(defaultUserDetails);

        String accessToken = getToken(defaultUserDetails, claims, ACCESS_TOKEN_VALIDATION_SECOND);
        String refreshToken = getToken(defaultUserDetails, claims, REFRESH_TOKEN_VALIDATION_SECOND);

        return new JwtToken(accessToken, refreshToken, BEARER_TYPE);
    }
    
    //id 데이터 저장한 클레임 객체 얻기
    public Claims getClaims(DefaultUserDetails defaultUserDetails) {
        Claims claims = Jwts.claims();
        claims.put("member_proper_num", defaultUserDetails.getUsername());
        claims.put("member_role", defaultUserDetails.getAuthorities());
        return claims;
    }
    
    //토큰 얻기
    private String getToken(DefaultUserDetails defaultUserDetails, Claims claims, Long validationSecond) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(defaultUserDetails.getUsername())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }

}