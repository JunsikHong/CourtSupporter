package com.court.supporter.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_003VO;
import com.court.supporter.command.TB_016VO;
import com.court.supporter.util.Criteria;

public interface noticeService {

	public ArrayList<TB_003VO> noticeList(Criteria cri); //목록
	public int getTotal(Criteria cri); //전체 데이터(페이징) 가져오기
	
	public int noticeRegist(TB_003VO vo, List<String> filelist); //멀티파일 업로드 추가해야 함
	
	public TB_003VO noticeDetail(String notice_proper_num);
	public List<TB_016VO> noticeFileDetail(String notice_proper_num);
	
	public TB_003VO noticeGetNext(String notice_proper_num);
	public TB_003VO noticeGetPrev(String notice_proper_num);
	
	public int noticeModify(TB_003VO vo);
	public int noticeUpdate(TB_003VO vo, List<String> filelist);
	
	public void noticeDelete(String notice_proper_num);
	
}
