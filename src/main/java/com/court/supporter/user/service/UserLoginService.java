package com.court.supporter.user.service;

import com.court.supporter.command.TB_018VO;
import com.court.supporter.security.jwt.JwtToken;

public interface UserLoginService {
	//로그인
	JwtToken login(TB_018VO tb_018vo);
}