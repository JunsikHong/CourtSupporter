package com.court.supporter.announce.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;

@Mapper
 interface AnnounceMapper {

	 //공고 등록
	 public int announceRegist(TB_002VO tb_002vo); //공고 등록
//	 public void createAnnouncemen(TB_002VO tb_002vo); //게시글 작성일
//	 public void updateAnnouncement(TB_002VO tb_002vo);	//게시글 수정일
//	
	 //공고 목록 조회	
	 public ArrayList<TB_002VO> announce_getList(@Param("cri") Criteria cri ); //공고 게시판(목록) //@Param("cri")
	 public int announce_getTotal(@Param("cri") Criteria cri);	
	
	 //공고 검색
//	 public List<TB_002VO> searchList(String keyword, Criteria cri);

//	  public int getTotalByKeyword(String writer, String keyword);
	
	
	 public TB_002VO getDetail(int announce_proper_num); //공고 상세(게시글)	
	
	 public int announceModify(TB_002VO vo); //공고 수정
	 
	 //공고 삭제
	 public  void announceDelete(int announce_proper_num);
	 //public void announceDelete(TB_002VO vo); 
}
