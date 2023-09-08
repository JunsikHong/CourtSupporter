package com.court.supporter.faq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_004VO;
import com.court.supporter.util.Criteria;

public interface faqService {

	public ArrayList<TB_004VO> faqList(String writer, Criteria cri); //목록
	public int getTotal(String writer, Criteria cri); //전체 데이터(페이징) 가져오기
	
	public int faqRegist(TB_004VO vo);
	
	public TB_004VO faqDetail(int faq_proper_num);
	
	public int faqModify(TB_004VO vo);
	public int faqUpdate(TB_004VO vo);
	
	public void faqDelete(int faq_proper_num);
}
