package com.court.supporter.application.service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_010VO;

public interface ApplicationService {

	//기본 정보 페이지 - 신청인 기본 정보 불러오기
	public TB_001VO getUserInfo(String user_id);
	//기본 정보 페이지 - 주민번호 조회
	public boolean getUserRrn(String user_rrn);
	//기본 정보 등록
	public void basicRegist(TB_001VO tb_001vo, TB_005VO tb_005vo, TB_010VO tb_010vo);
	
	
}
