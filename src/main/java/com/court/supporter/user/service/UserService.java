package com.court.supporter.user.service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.security.jwt.JwtToken;

public interface UserService {
   //회원가입
   int join(TB_001VO tb_001vo);
   //로그인 시 아이디로 회원 찾기
   TB_018VO findByMemberId(String memberId);
   //세션에서 claims 받을 때 claims의 proper_num로 회원 찾기
   TB_018VO findByMemberProperNum(String memberProperNum);
}