package com.court.supporter.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;

public interface ApplicationService {

	//기본 정보 페이지 - 신청인 기본 정보 불러오기
	public TB_001VO getUserInfo(String user_id);
	//기본 정보 페이지 - 주민번호 조회
	public boolean getUserRrn(String user_id, String user_name, String user_rrn);
	//기본 정보 등록
	public void basicRegist(TB_001VO tb_001vo, TB_005VO tb_005vo, TB_010VO tb_010vo);
	//학력 정보 등록
	public void educationRegist(TB_006VO tb_006vo);
	//학력 정보 페이지 - 입력한 학력 정보 불러오기
	public ArrayList<TB_006VO> getEducationList(String user_id);
	//학력 정보 수정 팝업 - 학력 정보 데이터 불러오기
	public TB_006VO getEducationInfo(String user_id, int edctn_dtls_proper_num);
	//학력 정보 페이지 - 학력 정보 수정
	public void educationModify(TB_006VO tb_006vo);
	//학력 정보 페이지 - 학력 정보 삭제
	public void educationInfoDelete(int edctn_dtls_proper_num, String user_id);
	//학력 정보 페이지 - 최종 학력 입력
	public void finalEducation(int final_education_chk, String user_id);
	//학력 정보 페이지 - 학력 정보 첨부 파일 등록
	public void educationAttachment(List<String> fileList, TB_009VO tb_009vo);
	//경력 정보 등록
	public void workRegist(TB_007VO tb_007vo);
	//경력 정보 페이지 - 입력한 경력 정보 불러오기
	public ArrayList<TB_007VO> getWorkList(String user_id);
	//경력 정보 수정 팝업 - 경력 정보 데이터 불러오기
	public TB_007VO getWorkInfo(String user_id, int aplcn_carer_proper_num);
	//경력 정보 페이지 - 경력 정보 수정
	public void workModify(TB_007VO tb_007vo);
	//경력 정보 페이지 - 경력 정보 삭제
	public void workInfoDelete(int aplcn_carer_proper_num, String user_id);
	//경력 정보 페이지 - 활동 경력, 특기 사항 등록
	public void workEtcRegist(TB_007VO tb_007vo);
	//경력 정보 페이지 - 활동 경력, 특기 사항 데이터 불러오기
	public TB_007VO getWorkEtc(int aplcn_dtls_proper_num, String user_id);
	//자격증 정보 페이지 - 자격증 정보 등록
	public void certificateRegist(TB_008VO tb_008vo);
	//자격증 정보 페이지 - 입력한 자격증 정보 불러오기
	public ArrayList<TB_008VO> getCertificateList(String user_id);
	//자격증 정보 수정 팝업 - 자격증 정보 데이터 불러오기
	public TB_008VO getCertificateInfo(String user_id, int aplcn_crtfc_proper_num);
	//자격증 정보 페이지 - 자격증 정보 수정
	public void certificateModify(TB_008VO tb_008vo);
	//자격증 정보 페이지 - 자격증 정보 삭제
	public void certificateInfoDelete(int aplcn_crtfc_proper_num, String user_id);
}
