package com.court.supporter.faq;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_004VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface faqMapper {

	public ArrayList<TB_004VO> faqList(@Param("writer") String writer, 
									@Param("cri") Criteria cri); //목록
	public int getTotal(@Param("writer") String writer, 
						@Param("cri") Criteria cri); //전체 데이터(페이징) 가져오기
	
	public TB_004VO faqDetail(int faq_proper_num);
	public int faqRegist(TB_004VO vo, List<MultipartFile> list); //멀티파일 업로드 추가해야 함
	
	public int faqModify(TB_004VO vo);
	public void faqDelete(int faq_proper_num);
}
