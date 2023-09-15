package com.court.supporter.adminmypage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.court.supporter.aws.service.EmailService;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_013VO;
import com.court.supporter.command.TB_015VO;
import com.court.supporter.command.TB_018VO;
import com.court.supporter.util.Criteria;

@Service("adminMypageService")
public class AdminMypageServiceImpl implements AdminMypageService {

	@Autowired
	private AdminMypageMapper adminMypageMapper;
	
	@Autowired
	@Qualifier("emailService")
	private EmailService emailService;

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return 0; //0 반환 -> 상대평가 충족조건 불만족 
	}

	@Override
	public int adminmypage_evaluate(TB_013VO vo) {
		return adminMypageMapper.adminmypage_evaluate(vo);
	}

	@Override
	public int adminmypage_juris_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO) {
		if(tb_005VO.getTrial_fcltt_proper_num() == 1010509 || 
		   tb_005VO.getTrial_fcltt_proper_num() == 1010408) {
			//최고점 27 -> 최저 15
			if(tb_013vo.getEvaluate_score() < 15) { //서류 반려 04
				emailService.sendEvaluationEmail(tb_005VO, "04");  //해당 신청자에게 반려 됐다 메일주기
				return adminMypageMapper.adminmypage_juris_companion_evaluate(tb_013vo); //반려처리
			}
		} else {
			//최고점 32 -> 최저 20
			if(tb_013vo.getEvaluate_score() < 20) { //서류 반려 04
				emailService.sendEvaluationEmail(tb_005VO, "04"); //해당 신청자에게 반려 됐다 메일주기
				return adminMypageMapper.adminmypage_juris_companion_evaluate(tb_013vo); //반려처리		
			}
		}
		return adminMypageMapper.adminmypage_juris_examination_evaluate(tb_013vo); //1차평가 심사중 03 (최저점보다 낮지 않은 경우)
	}

	@Override
	public int adminmypage_court_evaluate(TB_013VO tb_013vo, TB_005VO tb_005VO) {
		if(tb_005VO.getTrial_fcltt_proper_num() == 1010509 || 
		   tb_005VO.getTrial_fcltt_proper_num() == 1010408) {
			//최고점 27 -> 최저 15
			if(tb_013vo.getEvaluate_score() < 15) { //불합격 07
				emailService.sendEvaluationEmail(tb_005VO, "07"); //해당 신청자에게 불합격 됐다 메일주기
				return adminMypageMapper.adminmypage_court_referral_evaluate(tb_013vo);
			}
		} else {
			//최고점 32 -> 최저 20
			if(tb_013vo.getEvaluate_score() < 20) { //불합격 07
				emailService.sendEvaluationEmail(tb_005VO, "07"); //해당 신청자에게 불합격 됐다 메일주기
				return adminMypageMapper.adminmypage_court_referral_evaluate(tb_013vo);
			}
		}
		return adminMypageMapper.adminmypage_court_examination_evaluate(tb_013vo); //최종평가 심사중 06 (최저점보다 낮지 않은 경우)
	}

}
