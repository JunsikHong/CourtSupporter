package com.court.supporter.user.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_018VO;

public interface UserService {
	//회원가입
	int join(TB_001VO tb_001vo);
	//로그인 시 아이디로 회원 찾기
	TB_018VO findByMemberId(String memberId);
	//로그인 시 아이디로 user 탈퇴 여부 확인
	TB_001VO findByUserId(String userId);
	//세션에서 claims 받을 때 claims의 proper_num로 회원 찾기
	TB_018VO findByMemberProperNum(String memberProperNum);
	//회원가입 시 아이디 중복 확인
	int checkId(String userId);
	//메일 인증
	String sendMail(TB_001VO tb_001vo);
	//아이디 찾기 정보에 맞는 user 확인
	String findUsersForId(TB_001VO tb_001vo);
	//비밀번호 찾기 정보에 맞는 user 확인
	String findUsersForPw(TB_001VO tb_001vo);
	//신청 완료 메일 보내기
	public MimeMessage createMessage(TB_001VO tb_001VO, TB_005VO tb_005VO, String aplicn_dtls_sts)throws MessagingException, UnsupportedEncodingException;
	//비밀번호 재설정
	int updatePw(TB_001VO tb_001vo);
}

