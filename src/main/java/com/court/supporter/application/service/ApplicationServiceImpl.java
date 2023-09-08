package com.court.supporter.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;

	// 기본 정보 페이지 신청인 기본 정보 불러오기
	@Override
	public TB_001VO getUserInfo(String user_id) {
		return applicationMapper.getUserInfo(user_id);
	}

	//기본 정보 페이지 - 주민번호 조회
	@Override
	public boolean getUserRrn(String user_id, String user_name, String user_rrn) {
		if (applicationMapper.getUserRrn(user_id, user_name, user_rrn) == null) {
			return false;
		} else {
			return true;
		}
	}

	// 기본 정보 등록
	@Override
	public void basicRegist(TB_001VO tb_001vo, TB_005VO tb_005vo, TB_010VO tb_010vo) {
		// 조력자 분류 코드 설정
		String[] trial_fcltt_clasifi_codes = { "0101", "0102", "0103", "0103", "0103", "0103", "0103", "0104", "0105",
				"0106", "0201", "0202", "0203", "0301", "0301", "0302", "0401", "0402", "0501", "0502" };

		String[] trial_fcltt_sbcls_codes = { "0000000", "0000000", "0103001", "0103002", "0103011", "0103012",
				"0103013", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0301001", "0301002",
				"0000000", "0000000", "0000000", "0000000", "0000000" };

		int[] resultValues = { 1010101, 1010202, 1010303, 1010304, 1010305, 1010306, 1010307, 1010408, 1010509, 1010610,
				2020111, 2020212, 2020313, 3030114, 3030115, 3030216, 4040117, 4040218, 5050119, 5050220 };

		for (int i = 0; i < trial_fcltt_clasifi_codes.length; i++) {
			if (tb_010vo.getTrial_fcltt_sbcls_code() == null) {
				tb_010vo.setTrial_fcltt_sbcls_code("0000000");
			}
			if (tb_010vo.getTrial_fcltt_clasifi_code().equals(trial_fcltt_clasifi_codes[i])
					&& tb_010vo.getTrial_fcltt_sbcls_code().equals(trial_fcltt_sbcls_codes[i])) {
				tb_005vo.setTrial_fcltt_proper_num(resultValues[i]);
				break; // 조건에 맞는 경우, 더 이상 반복할 필요 없음
			}
		}

		// 조력자 분류 코드, 조력자 추가 정보, 희망 법원 등록
		int length = tb_005vo.getCourt_proper_num_list().size();
		for (int i = 0; i < length; i++) {
			tb_005vo.setCourt_proper_num(tb_005vo.getCourt_proper_num_list().get(i));
			applicationMapper.detailRegist(tb_005vo);
		}
		// 기본 정보 페이지 신청인 기본 정보 수정
		String user_phone = tb_001vo.getUser_phone1() + "-" + tb_001vo.getUser_phone2() + "-"
				+ tb_001vo.getUser_phone3();
		tb_001vo.setUser_phone(user_phone);
		String user_email = tb_001vo.getUser_email1() + "@" + tb_001vo.getUser_email2();
		tb_001vo.setUser_email(user_email);
		applicationMapper.userInfoUpdate(tb_001vo);
	}

	//학력 정보 입력
	@Override
	public void educationRegist(TB_006VO tb_006vo) {
		
		applicationMapper.educationRegist(tb_006vo);
	}

	//학력 정보 페이지 - 입력한 학력 정보 불러오기
	@Override
	public ArrayList<TB_006VO> getEducationList(String user_id) {
		return applicationMapper.getEducationList(user_id);
	}
	
	//학력 정보 수정 팝업 - 학력 정보 데이터 불러오기
	@Override
	public TB_006VO getEducationInfo(String user_id, int edctn_dtls_proper_num) {
		return applicationMapper.getEducationInfo(user_id, edctn_dtls_proper_num);
	}
		
	//학력 정보 페이지 - 학력 정보 수정
	@Override
	public void educationModify(TB_006VO tb_006vo) {
		applicationMapper.educationModify(tb_006vo);
	}

	//학력 정보 페이지 - 학력 정보 삭제
	@Override
	public void educationInfoDelete(int edctn_dtls_proper_num, String user_id) {
		applicationMapper.educationInfoDelete(edctn_dtls_proper_num, user_id);
	}

	//학력 정보 페이지 - 최종 학력 입력
	@Override
	public void finalEducation(int final_education_chk, String user_id) {
		applicationMapper.finalEducation(final_education_chk, user_id);
	}
	
	//학력 정보 페이지 - 학력 정보 첨부 파일 등록
	@Override
	public void educationAttachment(List<String> fileList, TB_009VO tb_009vo) {
		
		for(String file: fileList) {
			tb_009vo.setFile_path(file.substring(0, file.lastIndexOf('/')));
			tb_009vo.setUuid(file.substring(file.lastIndexOf('/') + 1, file.indexOf('_')));
			tb_009vo.setOriginal_file_name(file.substring(file.indexOf('_') + 1));
			applicationMapper.educationAttachment(tb_009vo);
		}
		
	}
	
	//경력 정보 등록
	@Override
	public void workRegist(TB_007VO tb_007vo) {
		applicationMapper.workRegist(tb_007vo);
	}
	
	//경력 정보 페이지 - 입력한 경력 정보 불러오기
	@Override
	public ArrayList<TB_007VO> getWorkList(String user_id) {
		return applicationMapper.getWorkList(user_id);
	}

	//경력 정보 수정 팝업 - 경력 정보 데이터 불러오기
	@Override
	public TB_007VO getWorkInfo(String user_id, int aplcn_carer_proper_num) {
		return  applicationMapper.getWorkInfo(user_id, aplcn_carer_proper_num);
	}

	//경력 정보 페이지 - 경력 정보 수정
	@Override
	public void workModify(TB_007VO tb_007vo) {
		applicationMapper.workModify(tb_007vo);
	}

	//경력 정보 페이지 - 경력 정보 삭제
	@Override
	public void workInfoDelete(int aplcn_carer_proper_num, String user_id) {
		applicationMapper.workInfoDelete(aplcn_carer_proper_num, user_id);
	}

	//경력 정보 페이지 - 활동 경력, 특기 사항 등록
	@Override
	public void workEtcRegist(TB_007VO tb_007vo) {
		applicationMapper.workEtcRegist(tb_007vo);
	}

	//경력 정보 페이지 - 활동 경력, 특기 사항 데이터 불러오기
	@Override
	public TB_007VO getWorkEtc(int aplcn_dtls_proper_num, String user_id) {
		return applicationMapper.getWorkEtc(aplcn_dtls_proper_num, user_id);
	}

	//자격증 정보 페이지 - 자격증 정보 등록
	@Override
	public void certificateRegist(TB_008VO tb_008vo) {
		applicationMapper.certificateRegist(tb_008vo);
		
	}

	//자격증 정보 페이지 - 입력한 자격증 정보 불러오기
	@Override
	public ArrayList<TB_008VO> getCertificateList(String user_id) {
		return applicationMapper.getCertificateList(user_id);
	}

	//자격증 정보 수정 팝업 - 자격증 정보 데이터 불러오기
	@Override
	public TB_008VO getCertificateInfo(String user_id, int aplcn_crtfc_proper_num) {
		return applicationMapper.getCertificateInfo(user_id, aplcn_crtfc_proper_num);
	}

	//자격증 정보 페이지 - 자격증 정보 수정
	@Override
	public void certificateModify(TB_008VO tb_008vo) {
		applicationMapper.certificateModify(tb_008vo);
		
	}

	//자격증 정보 페이지 - 자격증 정보 삭제
	@Override
	public void certificateInfoDelete(int aplcn_crtfc_proper_num, String user_id) {
		applicationMapper.certificateInfoDelete(aplcn_crtfc_proper_num, user_id);
		
	}

	
}
