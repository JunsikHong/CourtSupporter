package com.court.supporter.usermypage.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.util.Criteria;

@Service("userMypageService")
public class UserMypageServiceImpl implements UserMypageService{

	@Autowired
	private UserMypageMapper userMypageMapper;
	
	//사용자 정보 가져오기
	@Override
	public TB_001VO usermypage_getInfo(String user_id) {
		return userMypageMapper.usermypage_getInfo(user_id);
	}

	//사용자 정보 수정
	@Override
	public int usermypage_modifyInfo(TB_001VO vo) {
		return userMypageMapper.usermypage_modifyInfo(vo);
	}

	//사용자 탈퇴
	@Override
	public int usermypage_withdrawl(TB_001VO vo) {
		return userMypageMapper.usermypage_withdrawl(vo);
	}

	@Override
	public int usermypage_modify_pw_check(TB_001VO vo) {
		System.out.println(vo.getUser_pw());
		TB_001VO result = userMypageMapper.usermypage_modify_pw_check(vo);
		System.out.println(result.getUser_pw());	
		if(vo.getUser_pw().equals(result.getUser_pw())) {
			return 1;
		} else {
			return 0;			
		}
	}

	//리스트 총합 구하기
	@Override
	public int usermypage_application_gettotal(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_application_gettotal(user_id, cri);
	}

	//리스트 목록 구하기
	@Override
	public ArrayList<TB_005VO> usermypage_application_getlist(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_application_getlist(user_id, cri);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_005VO usermypage_getapplicationdetail(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail(vo);
	}

	//사용자 신청 상세 정보 주변 가져오기
	@Override
	public TB_005VO usermypage_getnearapplicationdetail(TB_005VO vo) {
		return userMypageMapper.usermypage_getnearapplicationdetail(vo);
	}

}
