package com.court.supporter.adminmypage.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_015VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.util.Criteria;

@Service("adminMypageService")
public class AdminMypageServiceImpl implements AdminMypageService {

	@Autowired
	private AdminMypageMapper adminMypageMapper;

	//관리자 권한 검사
	@Override
	public String adminmypage_authcheck(String member_id) {
		return adminMypageMapper.adminmypage_authcheck(member_id);
	}

	//관리자페이지 사용자 신청 리스트 총합(사법)
	@Override
	public int adminmypage_juris_getapplicationtotal(Criteria cri) {
		return adminMypageMapper.adminmypage_juris_getapplicationtotal(cri);
	}

	//관리자페이지 사용자 신청 리스트(사법)
	@Override
	public ArrayList<TB_005VO> adminmypage_juris_getapplicationlist(Criteria cri) {
		return adminMypageMapper.adminmypage_juris_getapplicationlist(cri);
	}

	//관리자페이지 사용자 신청 리스트 총합(법원)
	@Override
	public int adminmypage_court_getapplicationtotal(Criteria cri) {
		return adminMypageMapper.adminmypage_court_getapplicationtotal(cri);
	}

	//관리자페이지 사용자 신청 리스트(법원)
	@Override
	public ArrayList<TB_005VO> adminmypage_court_getapplicationlist(Criteria cri) {
		return adminMypageMapper.adminmypage_court_getapplicationlist(cri);
	}

	//관리자페이지 사용자 신청 리스트 총합(전체)
	@Override
	public int adminmypage_admin_getapplicationtotal(Criteria cri) {
		return adminMypageMapper.adminmypage_admin_getapplicationtotal(cri);
	}

	//관리자페이지 사용자 신청 리스트(전체)
	@Override
	public ArrayList<TB_005VO> adminmypage_admin_getapplicationlist(Criteria cri) {
		return adminMypageMapper.adminmypage_admin_getapplicationlist(cri);
	}

}
