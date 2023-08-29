package com.court.supporter.faq;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.faqVO;
import com.court.supporter.util.CpCriteria;

@Mapper
public interface faqMapper {

	public ArrayList<faqVO> faqList(@Param("writer") String writer, 
									@Param("cri") CpCriteria cri); //목록
	public int getTotal(@Param("writer") String writer, 
						@Param("cri") CpCriteria cri); //전체 데이터(페이징) 가져오기
	
	public faqVO faqDetail(int faq_proper_num);
	public int faqRegist(faqVO vo); //멀티파일 업로드 추가해야 함
	
	public int faqModify(faqVO vo);
	public void faqDelete(int faq_proper_num);
}
