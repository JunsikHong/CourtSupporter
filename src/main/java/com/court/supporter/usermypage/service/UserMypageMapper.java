package com.court.supporter.usermypage.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface UserMypageMapper {

	public TB_001VO usermypage_getInfo(String user_id); //사용자 정보 가져오기
	public int usermypage_modifyInfo(TB_001VO vo); //사용자 정보 수정
	public int usermypage_withdrawl(TB_001VO vo); //사용자 탈퇴
	public TB_001VO usermypage_modify_pw_check(TB_001VO vo);// 사용자 비밀번호 확인
	public int usermypage_application_gettotal(@Param("user_id") String user_id, 
											   @Param("cri") Criteria cri); //리스트 총합 구하기
	public ArrayList<TB_005VO> usermypage_application_getlist(@Param("user_id") String user_id, 
															  @Param("cri") Criteria cri); //리스트 목록 구하기
	public TB_005VO usermypage_getapplicationdetail(TB_005VO vo); //사용자 신청 상세 정보 가져오기
	public TB_005VO usermypage_getnearapplicationdetail(TB_005VO vo); //사용자 신청 상세 정보 주변 가져오기
}
