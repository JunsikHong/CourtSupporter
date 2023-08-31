package com.court.supporter.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.noticeVO;
import com.court.supporter.util.Criteria;

public interface noticeService {

	public ArrayList<noticeVO> noticeList(String writer, Criteria cri); //목록
	public int getTotal(String writer, Criteria cri); //전체 데이터(페이징) 가져오기
	
	public noticeVO noticeDetail(int notice_proper_num);
	public int noticeRegist(noticeVO vo, List<MultipartFile> list); //멀티파일 업로드 추가해야 함
	
	public int noticeModify(noticeVO vo);
	public void noticeDelete(int notice_proper_num);
}
