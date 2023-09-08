package com.court.supporter.adminmypage.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_015VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface AdminMypageMapper {

	public String adminmypage_authcheck(String member_id); //관리자 권한 검사
	
	public int adminmypage_juris_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(사법)
	public ArrayList<TB_005VO> adminmypage_juris_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(사법)
	public int adminmypage_court_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(법원)
	public ArrayList<TB_005VO> adminmypage_court_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(법원)
	public int adminmypage_admin_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(전체)
	public ArrayList<TB_005VO> adminmypage_admin_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(전체)

}
