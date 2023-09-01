package com.court.supporter.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
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
	public boolean getUserRrn(String user_rrn) {
		if (applicationMapper.getUserRrn(user_rrn) == null) {
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

}
