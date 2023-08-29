package com.court.supporter.faq;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.faqVO;
import com.court.supporter.util.CpCriteria;

@Service("faqService")
public class faqServiceImpl implements faqService{

	@Autowired
	private faqMapper faqMapper;
	
	@Override
	public ArrayList<faqVO> faqList(String writer, CpCriteria cri) {
		
		return faqMapper.faqList(writer, cri);
	}

	@Override
	public int getTotal(String writer, CpCriteria cri) {
		
		return faqMapper.getTotal(writer, cri);
	}

	@Override
	public faqVO faqDetail(int faq_proper_num) {
		
		return faqMapper.faqDetail(faq_proper_num);
	}

	@Override
	public int faqRegist(faqVO vo) {
		
		int result = faqMapper.faqRegist(vo);
		
		return result;
	}

	@Override
	public int faqModify(faqVO vo) {
		
		return faqMapper.faqModify(vo);
	}

	@Override
	public void faqDelete(int faq_proper_num) {
		faqMapper.faqDelete(faq_proper_num);
		
	}

	



}
