package com.court.supporter.faq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_004VO;
import com.court.supporter.util.Criteria;

@Service("faqService")
public class faqServiceImpl implements faqService{

	@Autowired
	private faqMapper faqMapper;
	
	@Override
	public ArrayList<TB_004VO> faqList(String writer, Criteria cri) {
		
		return faqMapper.faqList(writer, cri);
	}

	@Override
	public int getTotal(String writer, Criteria cri) {
		
		return faqMapper.getTotal(writer, cri);
	}

	@Override
	public TB_004VO faqDetail(String faq_proper_num) {
		
		return faqMapper.faqDetail(faq_proper_num);
	}

	@Override
	public int faqRegist(TB_004VO vo) {
		
		int result = faqMapper.faqRegist(vo);
		
		return result;
	}

	@Override
	public int faqModify(TB_004VO vo) {
		
		return faqMapper.faqModify(vo);
	}

	@Override
	public int faqUpdate(TB_004VO vo) {
		// TODO Auto-generated method stub
		return faqMapper.faqUpdate(vo);
	}
	
	@Override
	public void faqDelete(String faq_proper_num) {
		faqMapper.faqDelete(faq_proper_num);
		
	}


	



}
