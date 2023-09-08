package com.court.supporter.announce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;

 public interface AnnounceService {

	//공고 등록
	 public int announceRegist(TB_002VO vo); 	
//	 public void createAnnouncemen(TB_002VO vo); //공고등록시 게시 날짜
//	 public void updateAnnouncement(TB_002VO vo); //공고등록 후 수정 날짜
//	
	 //공고 목록 조회	
	 public ArrayList<TB_002VO> announce_getList(@Param("cri") Criteria cri); //@Param("cri")
	 public int announce_getTotal(@Param("cri") Criteria cri); 

	 //공고 검색
//	 public List<TB_002VO> searchList(String keyword, Criteria cri);
//	 int getTotalByKeyword(String writer, String keyword);
	
	 
	//공고 상세(게시글)	
	 public TB_002VO getDetail(int announce_proper_num); 
	 
	 public int announceModify(TB_002VO vo); //공고 수정
	 
	 public void announceDelete(int announce_proper_num); //공고 삭제
	
}
