package com.court.supporter.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_003VO;
import com.court.supporter.util.Criteria;

public interface noticeService {

	public ArrayList<TB_003VO> noticeList(String writer, Criteria cri); //목록
	public int getTotal(String writer, Criteria cri); //전체 데이터(페이징) 가져오기
	
	public TB_003VO noticeDetail(int notice_proper_num);
	public int noticeRegist(TB_003VO vo, List<MultipartFile> list); //멀티파일 업로드 추가해야 함
	
	public int noticeModify(TB_003VO vo);
	public void noticeDelete(int notice_proper_num);
}
