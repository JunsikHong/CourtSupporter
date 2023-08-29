package com.court.supporter.notice;

import java.util.ArrayList;

import com.court.supporter.command.noticeVO;
import com.court.supporter.util.CpCriteria;

public interface noticeService {

	public ArrayList<noticeVO> noticeList(String writer, CpCriteria cri); //목록
	public int getTotal(String writer, CpCriteria cri); //전체 데이터(페이징) 가져오기
	
	public noticeVO noticeDetail(int notice_proper_num);
	public int noticeRegist(noticeVO vo); //멀티파일 업로드 추가해야 함
	
	public int noticeModify(noticeVO vo);
	public void noticeDelete(int notice_proper_num);
}
