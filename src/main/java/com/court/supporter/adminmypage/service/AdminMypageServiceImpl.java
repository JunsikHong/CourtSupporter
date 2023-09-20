package com.court.supporter.adminmypage.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.court.supporter.aws.service.EmailService;
import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_013VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.usermypage.service.UserMypageService;
import com.court.supporter.util.Criteria;

@Service("adminMypageService")
public class AdminMypageServiceImpl implements AdminMypageService {

	@Autowired
	private AdminMypageMapper adminMypageMapper;
	
	@Autowired
	@Qualifier("emailService")
	private EmailService emailService;

	@Autowired
	@Qualifier("userMypageService")
	private UserMypageService userMypageService;
	
	//관리자 권한 검사
	@Override
	public String adminmypage_authcheck(String member_proper_num) {
		return adminMypageMapper.adminmypage_authcheck(member_proper_num);
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

	@Override
	public TB_005VO adminmypage_getapplicationdetail(String aplcn_dtls_proper_num) {
		return adminMypageMapper.adminmypage_getapplicationdetail(aplcn_dtls_proper_num);
	}
	
	@Override
	public int adminmypage_checkevaluatecomplete(TB_005VO vo) {
		ArrayList<TB_005VO> list = adminMypageMapper.adminmypage_checkevaluatecomplete(vo);
		
		int first_count = 0;
		int final_count = 0;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAplicn_dtls_sts().equals("03") ||
			   list.get(i).getAplicn_dtls_sts().equals("04")) { //1차평가 모든 신청자 심사중 or 서류반려 상태라면?
				first_count++;
			} else if(list.get(i).getAplicn_dtls_date().equals("06") ||
					  list.get(i).getAplicn_dtls_date().equals("07")) {//최종평가 모든 신청자 최종심사중 상태라면?
				final_count++;
			}
		}
		
		if(first_count == list.size()) {
			return 1; //1차평가 모든 신청자 심사중 상태
		} else if(first_count != list.size()){
			return 2; //1차평가 모든 신청자가 심사중 상태는 아님
		}
		
		if(final_count == list.size()) {
			return 3; //최종평가 모든 신청자 최종심사중 상태
		} else if(final_count != list.size()) {
			return 4; //최종평가 모든 신청자가 최종심사중 상태는 아님
		}
		
		return 5; //에러
	}
	
	@Override
	public int adminmypage_checkannounceenddate(TB_005VO vo) {
		//오늘날짜
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);

		//공고종료날짜
		TB_002VO tb_002VO = adminMypageMapper.adminmypage_checkannounceenddate(vo);
		String announce_end = tb_002VO.getAnnounce_end_date();
		try {
            Date todaydate = dateFormat.parse(today);
            Date announce_end_date = dateFormat.parse(announce_end);

            if (todaydate.after(announce_end_date)) { //오늘날짜가 공고종료날짜보다 이후라면 
                return 1; //1 반환 -> 상대평가 충족조건 만족 
            } else {
            	return 0; //0 반환 -> 상대평가 충족조건 불만족 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return 2; 
	}

	@Override
	public int adminmypage_evaluate(TB_013VO vo) {
		return adminMypageMapper.adminmypage_evaluate(vo);
	}

	@Override
	public int adminmypage_juris_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO, TB_001VO tb_001VO) {
		if(tb_005VO.getTrial_fcltt_proper_num() == 1010509 || 
		   tb_005VO.getTrial_fcltt_proper_num() == 1010408) {
			//최고점 27 -> 최저 15
			if(tb_013vo.getEvaluate_score() < 15) { //서류 반려 04
				try {
					emailService.createMessage(tb_001VO, tb_005VO, "04");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} //해당 신청자에게 반려 됐다 메일주기
				return adminMypageMapper.adminmypage_juris_companion_evaluate(tb_013vo); //반려처리
			}
		} else {
			//최고점 32 -> 최저 20
			if(tb_013vo.getEvaluate_score() < 20) { //서류 반려 04
				try {
					emailService.createMessage(tb_001VO, tb_005VO, "04");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} //해당 신청자에게 반려 됐다 메일주기
				return adminMypageMapper.adminmypage_juris_companion_evaluate(tb_013vo); //반려처리		
			}
		}
		return adminMypageMapper.adminmypage_juris_examination_evaluate(tb_013vo); //1차평가 심사중 03 (최저점보다 낮지 않은 경우)
	}

	@Override
	public int adminmypage_court_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO, TB_001VO tb_001VO) {
		if(tb_005VO.getTrial_fcltt_proper_num() == 1010509 || 
		   tb_005VO.getTrial_fcltt_proper_num() == 1010408) {
			//최고점 27 -> 최저 15
			if(tb_013vo.getEvaluate_score() < 15) { //불합격 07
				try {
					emailService.createMessage(tb_001VO, tb_005VO, "07");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} //해당 신청자에게 불합격 됐다 메일주기
				return adminMypageMapper.adminmypage_court_referral_evaluate(tb_013vo);
			}
		} else {
			//최고점 32 -> 최저 20
			if(tb_013vo.getEvaluate_score() < 20) { //불합격 07
				try {
					emailService.createMessage(tb_001VO, tb_005VO, "07");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} //해당 신청자에게 불합격 됐다 메일주기
				return adminMypageMapper.adminmypage_court_referral_evaluate(tb_013vo);
			}
		}
		return adminMypageMapper.adminmypage_court_examination_evaluate(tb_013vo); //최종평가 심사중 06 (최저점보다 낮지 않은 경우)
	}

	@Override
	public void adminmypage_confirm_first_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO, TB_001VO tb_001VO) {
		ArrayList<TB_013VO> result = adminMypageMapper.adminmypage_juris_getexamination(tb_005VO.getAnnounce_proper_num());
		int recruit_num = adminMypageMapper.adminmypage_getrecruitnum(tb_005VO); // 모집인원정보
	    result.sort((vo1, vo2) -> Double.compare(vo2.getEvaluate_score(), vo1.getEvaluate_score()));
	    for (int i = 0; i < Math.min(recruit_num*2, result.size()); i++) { //모집인원의 2배수로 1차합격
	    	TB_001VO vo1 = userMypageService.usermypage_getInfo(result.get(i).getUser_proper_num());
	    	TB_005VO vo5 = adminMypageMapper.adminmypage_getaplcninfo(result.get(i).getUser_proper_num(), tb_005VO.getAnnounce_proper_num());
	    	try {
				emailService.createMessage(vo1, vo5, "05");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} //해당 신청자에게 1차합격 됐다 메일주기
	    	
	    	//해당 신청자의 아이디와 공고 번호를 토대로 -> tb5번의 해당신청자의 아이디와 공고번호 모두 05로 업데이트
	    	adminMypageMapper.adminmypage_juris_pass_evaluate(result.get(i).getUser_proper_num(), tb_005VO.getAnnounce_proper_num()); // 재판조력자 1차합격
	    }
		
	}

	@Override
	public void adminmypage_confirm_final_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO, TB_001VO tb_001VO) {
		ArrayList<TB_013VO> result = adminMypageMapper.adminmypage_court_getexamination(tb_005VO.getAnnounce_proper_num());
		int recruit_num = adminMypageMapper.adminmypage_getrecruitnum(tb_005VO); //모집인원정보
	    result.sort((vo1, vo2) -> Double.compare(vo2.getEvaluate_score(), vo1.getEvaluate_score()));
	    for (int i = 0; i < Math.min(recruit_num, result.size()); i++) { //정보개수만큼 반복 돌려서
	    	TB_001VO vo1 = userMypageService.usermypage_getInfo(result.get(i).getUser_proper_num());
	    	TB_005VO vo5 = adminMypageMapper.adminmypage_getaplcninfo(result.get(i).getUser_proper_num(), tb_005VO.getAnnounce_proper_num());
	    	try {
	    		emailService.createMessage(vo1, vo5, "08");
	    	} catch (UnsupportedEncodingException e) {
	    		e.printStackTrace();
	    	} catch (MessagingException e) {
	    		e.printStackTrace();
	    	} //해당 신청자에게 최종합격 됐다 메일주기
	    	
	    	//해당 신청자의 아이디와 공고 번호를 토대로 -> tb5번의 해당신청자의 아이디와 공고번호 모두 08로 업데이트
	    	adminMypageMapper.adminmypage_court_accept_evaluate(result.get(i).getUser_proper_num(), tb_005VO.getAnnounce_proper_num()); // 재판조력자 1차합격
	    	
	    	ArrayList<TB_005VO> list = adminMypageMapper.adminmypage_getaceeptinfo(result.get(i).getUser_proper_num(), tb_005VO.getAnnounce_proper_num()); //최종합격한 정보 가져오기
	    	for(int j = 0; j < list.size(); j++) {
	    		//tb5에서 신청자아이디 공고번호를 토대로 -> 모두 list로 가져와서 -> 반복을 돌리면서 법원, 조력자번호, 유저번호를 14번에 모두 넣기
	    		adminMypageMapper.adminmypage_accept(list.get(i));//재판조력자 등재 (중복데이터 있음, 법원만 다름)	    		    		
	    	}

	    }
		
	}

	@Override
	public TB_013VO adminmypage_getresult(TB_005VO vo) {
		return adminMypageMapper.adminmypage_getresult(vo);
	}

	@Override
	public int adminmypage_getauthtotal(Criteria cri) {
		return adminMypageMapper.adminmypage_getauthtotal(cri);
	}

	@Override
	public ArrayList<TB_018VO> adminmypage_getauthlist(Criteria cri) {
		return adminMypageMapper.adminmypage_getauthlist(cri);
	}

	@Override
	public int adminmypage_updateauth(TB_018VO vo) {
		return adminMypageMapper.adminmypage_updateauth(vo);
	}

	@Override
	public String adminmypage_getmember_id(String member_proper_num) {
		return adminMypageMapper.adminmypage_getmember_id(member_proper_num);
	}


}