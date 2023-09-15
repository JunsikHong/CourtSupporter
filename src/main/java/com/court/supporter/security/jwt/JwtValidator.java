package com.court.supporter.security.jwt;

import java.security.Key;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.court.supporter.command.TB_018VO;
import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidator {
   
   private final Key key;
   private final UserService userService;
   
   public Authentication getAuthentication(String accessToken) {
      Claims claims = getTokenClaims(accessToken);
      System.out.println("claims: " + claims.get("member_proper_num", String.class));
      DefaultUserDetails defaultUserDetails = new DefaultUserDetails(userService.findByMemberProperNum(claims.get("member_proper_num", String.class)));
      return new UsernamePasswordAuthenticationToken(defaultUserDetails, "", defaultUserDetails.getAuthorities());
   }
   
   private Claims getTokenClaims(String token) {
      return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
   }
}