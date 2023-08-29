package com.court.supporter.faq;

import java.util.ArrayList;

import com.court.supporter.command.faqVO;
import com.court.supporter.util.CpCriteria;

public interface faqService {

	public ArrayList<faqVO> faqList(String writer, CpCriteria cri); //목록
	public int getTotal(String writer, CpCriteria cri); //전체 데이터(페이징) 가져오기
	
	public faqVO faqDetail(int faq_proper_num);
	public int faqRegist(faqVO vo); //멀티파일 업로드 추가해야 함
	
	public int faqModify(faqVO vo);
	public void faqDelete(int faq_proper_num);
}
