package com.court.supporter.usermypage.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;
import com.court.supporter.command.TB_012VO;
import com.court.supporter.command.TB_014VO;
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

	//신청 리스트 총합 구하기
	@Override
	public int usermypage_application_gettotal(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_application_gettotal(user_id, cri);
	}

	//신청 리스트 목록 구하기
	@Override
	public ArrayList<TB_005VO> usermypage_application_getlist(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_application_getlist(user_id, cri);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_005VO usermypage_getapplicationdetail1(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail1(vo);
	}
	
	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_006VO usermypage_getapplicationdetail2(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail2(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_007VO> usermypage_getapplicationdetail3(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail3(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_008VO> usermypage_getapplicationdetail4(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail4(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_009VO> usermypage_getapplicationdetail5(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail5(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_010VO usermypage_getapplicationdetail6(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail6(vo);
	}
	
	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_011VO> usermypage_getapplicationdetail7(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail7(vo);
	}
	
	//활동 리스트 총합 구하기
	@Override
	public int usermypage_activity_gettotal(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_activity_gettotal(user_id, cri);
	}
	
	//활동 리스트 목록 구하기
	@Override
	public ArrayList<TB_012VO> usermypage_activity_getlist(String user_id, Criteria cri) {
		return userMypageMapper.usermypage_activity_getlist(user_id, cri);
	}

	//사용자 활동 상세 정보 가져오기
	@Override
	public TB_012VO usermypage_getactivitydetail(TB_012VO vo) {
		return userMypageMapper.usermypage_getactivitydetail(vo);
	}

	//사용자 활동 상세 정보 주변 가져오기
	@Override
	public TB_012VO usermypage_getnearactivitydetail(TB_012VO vo) {
		return userMypageMapper.usermypage_getnearactivitydetail(vo);
	}

	//사용자 중지 리스트 총합구하기
	@Override
	public int usermypage_pausetotal(int user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_pausetotal(user_proper_num, cri);
	}

	//사용자 중지 리스트 가져오기
	@Override
	public ArrayList<TB_014VO> usermypage_pauselist(int user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_pauselist(user_proper_num, cri);
	}

	//사용자 중지 상세 정보 가져오기
	@Override
	public TB_014VO usermypage_getpausedetail(TB_014VO vo) {
		return userMypageMapper.usermypage_getpausedetail(vo);
	}

	//사용자 중지/해제 신청
	@Override
	public int usermypage_pauseapplication(TB_014VO vo) {
		System.out.println(vo.getAccept_act_yn());
		if(vo.getAccept_act_yn().equals("Y")) {
			return userMypageMapper.usermypage_pauseapplication(vo);			
		} else {
			return userMypageMapper.usermypage_cancelapplication(vo);
		}
	}

}
