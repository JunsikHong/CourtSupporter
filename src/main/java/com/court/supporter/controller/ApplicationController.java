package com.court.supporter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.application.service.ApplicationService;
import com.court.supporter.aws.service.ApplicationFileService;
import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	@Qualifier("applicationService")
	private ApplicationService applicationService;
	
	@Autowired
	@Qualifier("applicationFileService")
	private ApplicationFileService applicationFileService;
	
	
	//동의 화면
	@GetMapping("/applicationAgree")
	public String agreeForm() {
		return "application/applicationAgree";
	}
	
	//기본 정보 입력 화면으로 이동
	@PostMapping("/agreeForm")
	public String basic() {
		return "redirect:/application/applicationBasic";
	}
	
	//기본 정보 화면
	@GetMapping("/applicationBasic")
	public String basicForm(Model model) {
		String user_id = "user1";
		//신청인 정보 가져오기
		TB_001VO vo = applicationService.getUserInfo(user_id);
		
		model.addAttribute("vo", vo);
		
		return "application/applicationBasic";
	}
	
	//기본 정보 페이지 - 주민번호 조회
	@PostMapping("/fetchData")
	public ResponseEntity<String> fetchData(@RequestBody TB_001VO tb_001vo) {
		
        boolean isDataAvailable = applicationService.getUserRrn(tb_001vo.getUser_id(), tb_001vo.getUser_name(), tb_001vo.getUser_rrn());
        
        if (isDataAvailable) {
            return new ResponseEntity<>("{\"success\": true}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"success\": false}", HttpStatus.OK);
        }
    }
	
	//학력사항 입력 화면으로 이동
	@PostMapping("/basicForm")
	public String education(TB_001VO tb_001vo, TB_005VO tb_005vo, TB_010VO tb_010vo) {
		//기본 정보 등록
		applicationService.basicRegist(tb_001vo, tb_005vo, tb_010vo);
		
		return "redirect:/application/applicationEducation";
	}
	
	//학력사항 화면
	@GetMapping("/applicationEducation")
	public String educationForm(TB_006VO tb_006vo, Model model) {
		String user_id = "user1";
		//학력 정보 불러오기
		ArrayList<TB_006VO> list = applicationService.getEducationList(user_id);
		model.addAttribute("list", list);
		
		return "application/applicationEducation";
	}
	
	//학력사항 입력 팝업창
	@GetMapping("/applicationEducationPopup")
	public String educationPopupForm() {
		return "application/applicationEducationPopup";
	}
	
	//학력사항 등록
	@PostMapping("/educationPopupForm")
	public String educationPopup(TB_006VO tb_006vo) {
		tb_006vo.setUser_id("user1");
		
		applicationService.educationRegist(tb_006vo);
		return "redirect:/application/applicationEducation";
	}
	
	//학력사항 입력 정보 전송
	@PostMapping("/educationInfo")
	public ResponseEntity<ArrayList<TB_006VO>> educationInfo(@RequestBody TB_006VO tb_006vo) {
		
        ArrayList<TB_006VO> list = applicationService.getEducationList(tb_006vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//학력사항 수정 팝업창
	@GetMapping("/applicationEducationModifyPopup")
	public String educationModifyPopupForm(TB_006VO tb_006vo, Model model, @RequestParam("data") int data) {
		String user_id = "user1";
		
		tb_006vo.setEdctn_dtls_proper_num(data);
		
		//학력 정보 불러오기
		TB_006VO vo = applicationService.getEducationInfo(user_id, tb_006vo.getEdctn_dtls_proper_num());
		model.addAttribute("vo", vo);
		return "application/applicationEducationModifyPopup";
	}
	
	//학력사항 수정
	@PostMapping("/educationModifyPopupForm")
	public String educationModifyPopup(TB_006VO tb_006vo) {
		tb_006vo.setUser_id("user1");

		applicationService.educationModify(tb_006vo);
		return "redirect:/application/applicationEducation";
	}
	
	//학력 정보 삭제
	@PostMapping("/educationInfoDelete")
	public ResponseEntity<ArrayList<TB_006VO>> educationInfoDelete(@RequestBody TB_006VO tb_006vo) {
		
		applicationService.educationInfoDelete(tb_006vo.getEdctn_dtls_proper_num(), tb_006vo.getUser_id());
        ArrayList<TB_006VO> list = applicationService.getEducationList(tb_006vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//경력사항 입력 화면으로 이동
	@PostMapping("/educationForm")
	public String work(TB_006VO tb_006vo, TB_009VO tb_009vo, @RequestParam("file") List<MultipartFile> list ) {
		
		//최종학력 입력
		tb_006vo.setUser_id("user1");
		applicationService.finalEducation(tb_006vo.getFinal_education_chk(), tb_006vo.getUser_id());
		//파일 업로드
		tb_009vo.setUser_id(tb_006vo.getUser_id());
		tb_009vo.setAplcn_dtls_proper_num(1);
		tb_009vo.setFile_code("PE");
		tb_009vo.setFile_type("EDUCATIONLEVEL");
		System.out.println("파일이름:" + list.toString());
		List<String> fileList = applicationFileService.ApplicationFileRegist(list, tb_006vo.getUser_id());
		
//		applicationFileService.ApplicationFileDelete(keyNames);
		System.out.println(fileList);
		applicationService.educationAttachment(fileList, tb_009vo);
		return "redirect:/application/applicationWork";
	}
	
	//경력사항 입력 화면
	@GetMapping("/applicationWork")
	public String workForm(TB_007VO tb_007vo, Model model) {
		String user_id = "user1";
		tb_007vo.setAplcn_dtls_proper_num(1);
		TB_007VO vo = applicationService.getWorkEtc(tb_007vo.getAplcn_dtls_proper_num(), user_id);
		
		ArrayList<TB_007VO> list = applicationService.getWorkList(user_id);
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		return "application/applicationWork";
	}
	
	//경력사항 입력 팝업창
	@GetMapping("/applicationWorkPopup")
	public String workPopupForm() {
		
		return "application/applicationWorkPopup";
	}
	
	//경력 사항 등록
	@PostMapping("/workPopupForm")
	public String workPopup(TB_007VO tb_007vo) {
		tb_007vo.setUser_id("user1");
		tb_007vo.setAplcn_dtls_proper_num(1);
		applicationService.workRegist(tb_007vo);
		return "redirect:/application/applicationWork";
	}
	
	//경력사항 입력 정보 전송
	@PostMapping("/workInfo")
	public ResponseEntity<ArrayList<TB_007VO>> workInfo(@RequestBody TB_007VO tb_007vo) {
		
        ArrayList<TB_007VO> list = applicationService.getWorkList(tb_007vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//경력사항 수정 팝업창
	@GetMapping("/applicationWorkModifyPopup")
	public String workModifyPopupForm(TB_007VO tb_007vo, Model model, @RequestParam("data") int data) {
		String user_id = "user1";
		
		tb_007vo.setAplcn_carer_proper_num(data);
		
		//경력 정보 불러오기
		TB_007VO vo = applicationService.getWorkInfo(user_id, tb_007vo.getAplcn_carer_proper_num());
		model.addAttribute("vo", vo);
		return "application/applicationWorkModifyPopup";
	}
	
	//경력사항 수정
	@PostMapping("/workModifyPopupForm")
	public String workModifyPopup(TB_007VO tb_007vo) {
		tb_007vo.setUser_id("user1");

		applicationService.workModify(tb_007vo);
		return "redirect:/application/applicationWork";
	}
	
	//경력 정보 삭제
	@PostMapping("/workInfoDelete")
	public ResponseEntity<ArrayList<TB_007VO>> workInfoDelete(@RequestBody TB_007VO tb_007vo) {
		
		applicationService.workInfoDelete(tb_007vo.getAplcn_carer_proper_num(), tb_007vo.getUser_id());
        ArrayList<TB_007VO> list = applicationService.getWorkList(tb_007vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//자격증 정보 화면으로 이동
	@PostMapping("/workForm")
	public String certificate(TB_007VO tb_007vo) {
		tb_007vo.setUser_id("user1");
		tb_007vo.setAplcn_dtls_proper_num(1);
		applicationService.workEtcRegist(tb_007vo);
		return "redirect:/application/applicationCertificate";
	}
	
	//자격증 정보 입력 화면
	@GetMapping("/applicationCertificate")
	public String certificateForm(TB_008VO tb_008vo, Model model) {
		 tb_008vo.setUser_id("user1");
		 
		 ArrayList<TB_008VO> list = applicationService.getCertificateList(tb_008vo.getUser_id());
		 model.addAttribute("list", list);
		return "application/applicationCertificate";
	}
	
	//자격증 정보 입력 팝업창
	@GetMapping("/applicationCertificatePopup")
	public String certificatePopupForm() {
		
		return "application/applicationCertificatePopup";
	}
	
	//자격증 정보 등록
	@PostMapping("/certificatePopupForm")
	public String certificatePopup(TB_008VO tb_008vo) {
		tb_008vo.setUser_id("user1");
		tb_008vo.setAplcn_dtls_proper_num(1);
		applicationService.certificateRegist(tb_008vo);
		return "redirect:/application/applicationCertificate";
	}
	
	//자격증 입력 정보 전송
	@PostMapping("/certificateInfo")
	public ResponseEntity<ArrayList<TB_008VO>> certificateInfo(@RequestBody TB_008VO tb_008vo) {
		
        ArrayList<TB_008VO> list = applicationService.getCertificateList(tb_008vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//자격증 정보 수정 팝업창
	@GetMapping("/applicationCertificateModifyPopup")
	public String certificateModifyPopupForm(TB_008VO tb_008vo, Model model, @RequestParam("data") int data) {
		String user_id = "user1";
		
		tb_008vo.setAplcn_crtfc_proper_num(data);
		
		//자격증 정보 불러오기
		TB_008VO vo = applicationService.getCertificateInfo(user_id, tb_008vo.getAplcn_crtfc_proper_num());
		model.addAttribute("vo", vo);
		return "application/applicationCertificateModifyPopup";
	}
	
	//자격증 정보 수정
	@PostMapping("/certificateModifyPopupForm")
	public String certificateModifyPopup(TB_008VO tb_008vo) {
		tb_008vo.setUser_id("user1");

		applicationService.certificateModify(tb_008vo);
		return "redirect:/application/applicationCertificate";
	}
	
	//자격증 정보 삭제
	@PostMapping("/certificateInfoDelete")
	public ResponseEntity<ArrayList<TB_008VO>> certificateInfoDelete(@RequestBody TB_008VO tb_008vo) {
		
		applicationService.certificateInfoDelete(tb_008vo.getAplcn_crtfc_proper_num(), tb_008vo.getUser_id());
        ArrayList<TB_008VO> list = applicationService.getCertificateList(tb_008vo.getUser_id());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	//증빙서류 화면으로 이동
	@PostMapping("/certificateForm")
	public String attachment() {
		return "redirect:/application/applicationAttachment";
	}
	
	//증빙서류 입력 화면
	@GetMapping("/applicationAttachment")
	public String attachmentForm() {
		return "application/applicationAttachment";
	}
	
}
