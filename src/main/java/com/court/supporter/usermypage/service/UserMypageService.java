package com.court.supporter.usermypage.service;


import java.util.ArrayList;

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
import com.court.supporter.command.TB_019VO;
import com.court.supporter.util.Criteria;

public interface UserMypageService {

	public TB_001VO usermypage_getInfo(String user_proper_num); //사용자 정보 가져오기
	public int usermypage_modifyInfo(TB_001VO vo); //사용자 정보 수정
	public int usermypage_registimg(String savepath, TB_019VO vo); //사용자 이미지 등록
	public TB_019VO usermypage_getimg(String user_proper_num); //사용자 이미지 가져오기
	
	public int usermypage_withdrawl(TB_001VO vo); //사용자 탈퇴
	public int usermypage_modify_pw_check(TB_001VO vo);// 사용자 비밀번호 확인
	
	public int usermypage_application_gettotal(String user_id, Criteria cri); //사용자 신청 리스트 총합 구하기
	public ArrayList<TB_005VO> usermypage_application_getlist(String user_proper_num, Criteria cri); //사용자 신청 리스트 목록 구하기
	public TB_005VO usermypage_getapplicationdetail1 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public ArrayList<TB_006VO> usermypage_getapplicationdetail2 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public ArrayList<TB_007VO> usermypage_getapplicationdetail3 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public ArrayList<TB_008VO> usermypage_getapplicationdetail4 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public ArrayList<TB_009VO> usermypage_getapplicationdetail5 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public TB_010VO usermypage_getapplicationdetail6 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public ArrayList<TB_011VO> usermypage_getapplicationdetail7 (TB_005VO vo); //사용자 신청 상세 정보 가져오기
	
	public int usermypage_activity_gettotal(String user_proper_num, Criteria cri); //사용자 활동 리스트 총합 구하기
	public ArrayList<TB_012VO> usermypage_activity_getlist(String user_proper_num, Criteria cri); //사용자 활동 리스트 목록구하기
	public TB_012VO usermypage_getactivitydetail(TB_012VO vo); //사용자 활동 상세 정보 가져오기
	public TB_012VO usermypage_getnearactivitydetail(TB_012VO vo); //사용자 활동 상세 정보 주변 가져오기
	public ArrayList<TB_014VO> usermypage_getacceptinfo(String user_proper_num); //사용자 등재명단 가져오기 모두
	public int usermypage_activity_regist(TB_012VO vo); //사용자 활동 등록하기
	
	public int usermypage_pausetotal (String user_proper_num, Criteria cri); //사용자 중지 리스트 총합구하기
	public ArrayList<TB_014VO> usermypage_pauselist(String user_proper_num, Criteria cri); //사용자 중지 리스트 가져오기
	public TB_014VO usermypage_getpausedetail(TB_014VO vo); //사용자 중비 상세 정보 가져오기
	public int usermypage_pauseapplication (TB_014VO vo); //사용자 중지/해제 신청
}