package com.court.supporter.announce.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_017VO;
import com.court.supporter.util.Criteria;

 public interface AnnounceService {

	//공고 등록
	 public int announceRegist(TB_002VO vo, List<String> list ); 
	 public int getTrial_flctt_proper_num(TB_010VO tb_010VO);
	 
	 //공고 목록 조회	
	 public ArrayList<TB_002VO> announce_getList(@Param("cri") Criteria cri); //@Param("cri")
	 public int announce_getTotal(@Param("cri") Criteria cri); 
	 public ArrayList<TB_002VO> getTrialList(int category); //조력자 조회 TB_010VO tb_010VO or  int trial_fcltt_proper_num
	 
	 //공고 상세(게시글)	
	 public TB_002VO getDetail(String announce_proper_num); 
	 public List<TB_017VO> getFileDetail(String announce_proper_num );
	 
	 public TB_002VO getPrev(String announce_proper_num) ;
	 public TB_002VO getNext(String announce_proper_num) ;
	 
	 public int announceModify(TB_002VO vo); //공고 수정
	 
	 public void announceDelete(String announce_proper_num); //공고 삭제

	
}
