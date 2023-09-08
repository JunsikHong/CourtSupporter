package com.court.supporter.announce.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_017VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface AnnounceMapper {

	 //공고 등록
	 public int announceRegist(TB_002VO vo); //공고 등록 
	 public int announceFileRegist(TB_017VO fileVO);
  
	
	 //공고 목록 조회	
	 public ArrayList<TB_002VO> announce_getList(@Param("cri") Criteria cri ); //공고 게시판(목록) //@Param("cri")
	 public int announce_getTotal(@Param("cri") Criteria cri);	
	
	
	 public TB_002VO getDetail(int announce_proper_num); //공고 상세(게시글)	
	 public List<TB_017VO> getFileDetail(int announce_proper_num);
	 
	 public TB_002VO getPrev(int announce_proper_num) ;
	 public TB_002VO getNext(int announce_proper_num) ;
	 
	 
	 public int announceModify(TB_002VO vo); //공고 수정 
	 
	 public void announceDelete(int announce_proper_num); //공고 삭제

	
}
