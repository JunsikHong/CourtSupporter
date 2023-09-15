package com.court.supporter.adminmypage.service;

import java.util.ArrayList;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_013VO;
import com.court.supporter.command.TB_015VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.util.Criteria;

public interface AdminMypageService {

	public String adminmypage_authcheck(String member_id); //관리자 권한 검사
	
	public int adminmypage_juris_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(사법)
	public ArrayList<TB_005VO> adminmypage_juris_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(사법)
	public int adminmypage_court_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(법원)
	public ArrayList<TB_005VO> adminmypage_court_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(법원)
	public int adminmypage_admin_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(전체)
	public ArrayList<TB_005VO> adminmypage_admin_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(전체)
	
	public TB_005VO adminmypage_getapplicationdetail(String aplcn_dtls_proper_num); //관리자페이지 조력자신청 상세정보
	
	public int adminmypage_checkannounceenddate(TB_005VO vo); //공고기한 끝났는지 확인
	public int adminmypage_checkevaluatecomplete(TB_005VO vo); //모든 신청자가 심사중인지 확인
	
	public int adminmypage_evaluate(TB_013VO vo); //관리자페이지 신청 평가 점수 넣기
	public int adminmypage_juris_evaluate(TB_013VO tb_013VO, TB_005VO tb_005VO); //관리자페이지 신청 1차평가
	public int adminmypage_court_evaluate(TB_013VO tb_013VO, TB_005VO tb_005VO); //관리자페이지 신청 최종평가
}
