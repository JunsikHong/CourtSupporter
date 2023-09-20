package com.court.supporter.adminmypage.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_013VO;
import com.court.supporter.command.TB_015VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface AdminMypageMapper {

	public String adminmypage_authcheck(String member_proper_num); //관리자 권한 검사

	public int adminmypage_juris_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(사법)
	public ArrayList<TB_005VO> adminmypage_juris_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(사법)
	public int adminmypage_court_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(법원)
	public ArrayList<TB_005VO> adminmypage_court_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(법원)
	public int adminmypage_admin_getapplicationtotal(Criteria cri); //관리자페이지 사용자 신청 리스트 총합(전체)
	public ArrayList<TB_005VO> adminmypage_admin_getapplicationlist(Criteria cri); //관리자페이지 사용자 신청 리스트(전체)

	public TB_005VO adminmypage_getapplicationdetail(String aplcn_dtls_proper_num); //관리자페이지 조력자신청 상세정보
	
	public TB_002VO adminmypage_checkannounceenddate(TB_005VO vo); //공고기한 끝났는지 확인
	public ArrayList<TB_005VO> adminmypage_checkevaluatecomplete (TB_005VO vo); //해당 공고에 대해 모든 신청자가 심사중인지 확인
	
	public int adminmypage_evaluate(TB_013VO vo); //관리자페이지 신청 평가 점수 넣기
	
	public int adminmypage_juris_examination_evaluate(TB_013VO vo); //관리자페이지 신청 1차평가 심사중
	public int adminmypage_court_examination_evaluate(TB_013VO vo); //관리자페이지 신청 최종평가 심사중

	public int adminmypage_juris_companion_evaluate(TB_013VO vo); //관리자페이지 신청 1차평가 서류반려
	public int adminmypage_court_referral_evaluate(TB_013VO vo); //관리자페이지 신청 최종평가 불합격

	public ArrayList<TB_013VO> adminmypage_juris_getexamination(String announce_proper_num); //관리자페이지 1차평가 심사중인 신청자 가져오기
	public ArrayList<TB_013VO> adminmypage_court_getexamination(String announce_proper_num); //관리자페이지 최종평가 심사중인 신청자 가져오기
	
	public int adminmypage_juris_pass_evaluate(@Param("user_proper_num") String user_proper_num,
			 								   @Param("announce_proper_num") String announce_proper_num); //관리자페이지 신청 1차평가 합격
	public int adminmypage_court_accept_evaluate(@Param("user_proper_num") String user_proper_num,
												 @Param("announce_proper_num") String announce_proper_num); //관리자페이지 신청 최종평가 합격
	
	public ArrayList<TB_005VO> adminmypage_getacceptinfo(TB_013VO tb_013vo); //신청고유번호로 신청정보 모두 불러오기(법원+)
	public int adminmypage_getrecruitnum(TB_005VO tb_005VO); //관리자 페이지 공고 모집인원 불러오기
	public int adminmypage_accept(TB_005VO tb_005vo); //관리자페이지 최종등재
	
	public TB_013VO adminmypage_getresult (TB_005VO vo); //관리자 페이지 평가 결과
	
	public int adminmypage_getauthtotal(Criteria cri); //관리자페이지 관리자 리스트 총합
	public ArrayList<TB_018VO> adminmypage_getauthlist(Criteria cri); //관리자페이지 관리자 리스트
	public int adminmypage_updateauth(TB_018VO vo); //관리자 권한 설정하기
	
	public String adminmypage_getmember_id(String member_proper_num); //멤버 아이디 구하기
	
	public TB_005VO adminmypage_getaplcninfo(@Param("user_proper_num") String user_proper_num,
											 @Param("announce_proper_num") String announce_proper_num); //신청 테이블에서 신청 정보 가져오기
	
	public ArrayList<TB_005VO> adminmypage_getaceeptinfo (@Param("user_proper_num") String user_proper_num,
														  @Param("announce_proper_num") String announce_proper_num); //신청테이블에서 합격한 사람들 법원정보 및 모든정보 다 가져오기
}