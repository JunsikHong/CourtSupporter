package com.court.supporter.application.service;

import org.apache.ibatis.annotations.Mapper;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;

@Mapper
public interface ApplicationMapper {

	//기본 정보 페이지 - 신청인 기본 정보 불러오기
	public TB_001VO getUserInfo(String user_id);
	//기본 정보 페이지 - 주민번호 조회
	public String getUserRrn(String user_rrn);
	//기본 정보 - 조력자 분류 코드, 조력자 추가 정보, 희망 법원 등록
	public int detailRegist(TB_005VO tb_005vo);
	//기본 정보 페이지 신청인 기본 정보 수정
	public void userInfoUpdate(TB_001VO tb_001vo);
}
