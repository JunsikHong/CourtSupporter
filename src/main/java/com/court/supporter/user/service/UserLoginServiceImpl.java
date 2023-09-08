package com.court.supporter.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_018VO;
import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.security.jwt.JwtProvider;
import com.court.supporter.security.jwt.JwtToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtProvider jwtProvider;
	private final UserMapper userMapper;
	
	//로그인
	@Override
	public JwtToken login(TB_018VO tb_018vo) {
		TB_018VO findVO = userMapper.findByMemberId(tb_018vo.getMember_id()).orElseThrow(RuntimeException::new);
		if (bCryptPasswordEncoder.matches(tb_018vo.getMember_password(), findVO.getMember_password())) {
			return jwtProvider.createJwtToken(new DefaultUserDetails(findVO));
		}
		throw new RuntimeException();
	}

}
