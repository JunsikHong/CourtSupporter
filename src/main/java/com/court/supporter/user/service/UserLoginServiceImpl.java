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
		//입력한 아이디에 해당하는 데이터 받아오기
		TB_018VO findVO = userMapper.findByMemberId(tb_018vo.getMember_id()).orElseThrow(RuntimeException::new);
		System.out.println(findVO.getMember_role().equals("ROLE_USER"));
		//관리자일 때
		if (!findVO.getMember_role().equals("ROLE_USER")) {
			//해당하는 데이터의 비밀번호와 입력한 비밀번호가 같을 때 jwt토큰 발급
			if (bCryptPasswordEncoder.matches(tb_018vo.getMember_password(), findVO.getMember_password())) {
				return jwtProvider.createJwtToken(new DefaultUserDetails(findVO));
			}
		//사용자일 때
		} else {
			//해당하는 데이터의 비밀번호와 입력한 비밀번호가 같고, 탈퇴한 회원이 아닐 때 jwt토큰 발급
			if (userMapper.findByUserId(tb_018vo.getMember_id()).getUser_delete_yn().equals("N") 
					&& bCryptPasswordEncoder.matches(tb_018vo.getMember_password(), findVO.getMember_password())) {
				return jwtProvider.createJwtToken(new DefaultUserDetails(findVO));
			}
		}
		throw new RuntimeException();
	}

}
