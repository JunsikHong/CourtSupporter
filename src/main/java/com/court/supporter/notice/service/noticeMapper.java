package com.court.supporter.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_016VO;
import com.court.supporter.command.TB_003VO;
import com.court.supporter.util.Criteria;

@Mapper
public interface noticeMapper {

	public ArrayList<TB_003VO> noticeList(@Param("cri") Criteria cri); //목록
	public int getTotal(@Param("cri") Criteria cri); //전체 데이터(페이징) 가져오기
	
	public int noticeRegist(TB_003VO vo); //멀티파일 업로드 추가해야 함
	public String getnoticepropernum(TB_003VO vo);
	public void noticeFileRegist(TB_016VO vo); //파일등록
	
	public TB_003VO noticeDetail(String notice_proper_num); //멀티파일 업로드 추가해야 함
	public List<TB_016VO> noticeFileDetail(String notice_proper_num);
	
	public TB_003VO noticeGetNext(String notice_proper_num);
	public TB_003VO noticeGetPerv(String notice_proper_num);
	
	public int noticeModify(TB_003VO vo);
	public int noticeUpdate(TB_003VO vo); //멀티파일 업로드 추가해야 함
	
	
	public void noticeDelete(String notice_proper_num);
	public void noticeFileDelete(String notice_proper_num);
}
