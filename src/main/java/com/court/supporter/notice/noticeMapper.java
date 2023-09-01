package com.court.supporter.notice;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.noticeUploadVO;
import com.court.supporter.command.noticeVO;
import com.court.supporter.util.Criteria;

@Mapper
public interface noticeMapper {

	public ArrayList<noticeVO> noticeList(@Param("writer") String writer,
										  @Param("cri") Criteria cri); //목록
	public int getTotal(@Param("writer") String writer,
						@Param("cri") Criteria cri); //전체 데이터(페이징) 가져오기
	
	public noticeVO noticeDetail(int notice_proper_num);
	public int noticeRegist(noticeVO vo); //멀티파일 업로드 추가해야 함
	public void noticeFileRegist(noticeUploadVO vo); //파일등록
	
	public int noticeModify(noticeVO vo);
	public void noticeDelete(int notice_proper_num);
}
