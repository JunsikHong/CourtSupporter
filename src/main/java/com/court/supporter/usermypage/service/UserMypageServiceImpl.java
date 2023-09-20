package com.court.supporter.usermypage.service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;
import com.court.supporter.command.TB_012VO;
import com.court.supporter.command.TB_014VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.command.TB_019VO;
import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.security.jwt.JwtProvider;
import com.court.supporter.user.service.UserMapper;
import com.court.supporter.util.Criteria;

import lombok.RequiredArgsConstructor;

@Service("userMypageService")
@RequiredArgsConstructor
public class UserMypageServiceImpl implements UserMypageService{

	@Autowired
	private UserMypageMapper userMypageMapper;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//사용자 정보 가져오기
	@Override
	public TB_001VO usermypage_getInfo(String user_proper_num) {
		return userMypageMapper.usermypage_getInfo(user_proper_num);
	}

	//사용자 정보 수정
	@Override
	public int usermypage_modifyInfo(TB_001VO vo) {
		return userMypageMapper.usermypage_modifyInfo(vo);
	}

	
	//사용자 탈퇴
	@Override
	public int usermypage_withdrawl(TB_001VO vo) {
		TB_018VO findVO = userMypageMapper.usermypage_withdrawl_search(vo);
		if (bCryptPasswordEncoder.matches(vo.getUser_pw(), findVO.getMember_password())) {
			return userMypageMapper.usermypage_withdrawl(vo);			
		}
		return 0;
	}

	@Override
	public int usermypage_modify_pw_check(TB_001VO vo) {
		TB_001VO result = userMypageMapper.usermypage_modify_pw_check(vo);	
		if(vo.getUser_pw().equals(result.getUser_pw())) {
			return 1;
		} else {
			return 0;			
		}
	}

	//신청 리스트 총합 구하기
	@Override
	public int usermypage_application_gettotal(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_application_gettotal(user_proper_num, cri);
	}

	//신청 리스트 목록 구하기
	@Override
	public ArrayList<TB_005VO> usermypage_application_getlist(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_application_getlist(user_proper_num, cri);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_005VO usermypage_getapplicationdetail1(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail1(vo);
	}
	
	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_006VO> usermypage_getapplicationdetail2(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail2(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_007VO> usermypage_getapplicationdetail3(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail3(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_008VO> usermypage_getapplicationdetail4(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail4(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_009VO> usermypage_getapplicationdetail5(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail5(vo);
	}

	//사용자 신청 상세 정보 가져오기
	@Override
	public TB_010VO usermypage_getapplicationdetail6(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail6(vo);
	}
	
	//사용자 신청 상세 정보 가져오기
	@Override
	public ArrayList<TB_011VO> usermypage_getapplicationdetail7(TB_005VO vo) {
		return userMypageMapper.usermypage_getapplicationdetail7(vo);
	}
	
	//활동 리스트 총합 구하기
	@Override
	public int usermypage_activity_gettotal(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_activity_gettotal(user_proper_num, cri);
	}
	
	//활동 리스트 목록 구하기
	@Override
	public ArrayList<TB_012VO> usermypage_activity_getlist(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_activity_getlist(user_proper_num, cri);
	}

	//사용자 활동 상세 정보 가져오기
	@Override
	public TB_012VO usermypage_getactivitydetail(TB_012VO vo) {
		return userMypageMapper.usermypage_getactivitydetail(vo);
	}

	//사용자 활동 상세 정보 주변 가져오기
	@Override
	public TB_012VO usermypage_getnearactivitydetail(TB_012VO vo) {
		return userMypageMapper.usermypage_getnearactivitydetail(vo);
	}

	//사용자 중지 리스트 총합구하기
	@Override
	public int usermypage_pausetotal(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_pausetotal(user_proper_num, cri);
	}

	//사용자 중지 리스트 가져오기
	@Override
	public ArrayList<TB_014VO> usermypage_pauselist(String user_proper_num, Criteria cri) {
		return userMypageMapper.usermypage_pauselist(user_proper_num, cri);
	}

	//사용자 중지 상세 정보 가져오기
	@Override
	public TB_014VO usermypage_getpausedetail(TB_014VO vo) {
		return userMypageMapper.usermypage_getpausedetail(vo);
	}

	//사용자 중지/해제 신청
	@Override
	public int usermypage_pauseapplication(TB_014VO vo) {
		System.out.println(vo.getAccept_act_yn());
		if(vo.getAccept_act_yn().equals("Y")) {
			return userMypageMapper.usermypage_pauseapplication(vo);			
		} else {
			return userMypageMapper.usermypage_cancelapplication(vo);
		}
	}

	@Override
	public int usermypage_registimg(String savepath, TB_019VO tb_019VO) {
		
		String regex1 = "(.*?/.*?/)"; // 파일 저장 경로
		String regex2 = "([^/]+)_"; // UUID
		// String regex3 = "_(.*?)\\.[^.]+$"; //원본 파일 이름
		String regex3 = "_(.*)"; // 원본 파일 이름

		Pattern pattern1 = Pattern.compile(regex1); // 파일 저장 경로
		Pattern pattern2 = Pattern.compile(regex2); // UUID
		Pattern pattern3 = Pattern.compile(regex3); // 원본 파일 이름

		Matcher matcher1 = pattern1.matcher(savepath);
		Matcher matcher2 = pattern2.matcher(savepath);
		Matcher matcher3 = pattern3.matcher(savepath);

		if (matcher1.find() && matcher2.find() && matcher3.find()) {

			String file_path = matcher1.group(1);
			String img_file_uuid = matcher2.group(1);
			String original_file_name = matcher3.group(1);

			tb_019VO.setFile_path(file_path);
			//tb_016vo.setFile_type(file_type);
			tb_019VO.setImg_file_uuid(img_file_uuid);
			tb_019VO.setOriginal_file_name(original_file_name);
			
			System.out.println("file_path : " + file_path + " original_file_name : " + original_file_name
					+ " img_file_uuid : " + img_file_uuid + " user_proper_num : ");
		}
		
		int result = userMypageMapper.usermypage_registimg(tb_019VO);
		return result;
	}

	@Override
	public TB_019VO usermypage_getimg(String user_proper_num) {
		return userMypageMapper.usermypage_getimg(user_proper_num);
	}

	@Override
	public ArrayList<TB_014VO> usermypage_getacceptinfo(String user_proper_num) {
		return userMypageMapper.usermypage_getacceptinfo(user_proper_num);
	}

	@Override
	public int usermypage_activity_regist(TB_012VO vo) {
		return userMypageMapper.usermypage_activity_regist(vo);
	}

}

