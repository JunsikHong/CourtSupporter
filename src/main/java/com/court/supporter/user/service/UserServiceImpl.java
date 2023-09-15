package com.court.supporter.user.service;

import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_018VO;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   
   private final UserMapper userMapper;

   //회원가입
   @Override
   public int join(TB_001VO tb_001vo) {
      return userMapper.join(tb_001vo);
   }

   //로그인 시 아이디로 회원 찾기
   @Override
   public TB_018VO findByMemberId(String memberId) {
      return userMapper.findByMemberId(memberId).orElseThrow(RuntimeException::new);
   }

	//세션에서 claims 받을 때 claims의 proper_num로 회원 찾기
	@Override
	public TB_018VO findByMemberProperNum(String memberProperNum) {
		return userMapper.findByMemberProperNum(memberProperNum).orElseThrow(RuntimeException::new);
	}

}

