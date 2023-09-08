package com.court.supporter.announce.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_017VO;
import com.court.supporter.util.Criteria;

 public interface AnnounceService {

	//공고 등록
	 public int announceRegist(TB_002VO vo, List<String> list ); //, List<MultipartFile> list 	

	 
	 //공고 목록 조회	
	 public ArrayList<TB_002VO> announce_getList(@Param("cri") Criteria cri); //@Param("cri")
	 public int announce_getTotal(@Param("cri") Criteria cri); 
	 
	 //공고 상세(게시글)	
	 public TB_002VO getDetail(int announce_proper_num); 
	 public List<TB_017VO> getFileDetail(int announce_proper_num );
	 
	 public TB_002VO getPrev(int announce_proper_num) ;
	 public TB_002VO getNext(int announce_proper_num) ;
	 
	 public int announceModify(TB_002VO vo); //공고 수정
	 
	 public void announceDelete(int announce_proper_num); //공고 삭제
//	 public void updateAnnounceNum(int announce_proper_num); //공고 삭제시 글번호 업데이트
	
}
