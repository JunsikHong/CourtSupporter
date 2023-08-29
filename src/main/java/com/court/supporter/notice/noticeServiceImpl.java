package com.court.supporter.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.noticeVO;
import com.court.supporter.util.CpCriteria;

@Service("noticeService")
public class noticeServiceImpl implements noticeService {

	@Autowired
	private noticeMapper noticeMapper;
	
	@Override
	public ArrayList<noticeVO> noticeList(String writer, CpCriteria cri) {
		
		return noticeMapper.noticeList(writer, cri);
	}

	@Override
	public int getTotal(String writer, CpCriteria cri) {
		
		return noticeMapper.getTotal(writer, cri);
	}

	@Override
	public noticeVO noticeDetail(int notice_proper_num) {
		
		return noticeMapper.noticeDetail(notice_proper_num);
	}

	@Override
	public int noticeRegist(noticeVO vo) {
		
		return noticeMapper.noticeRegist(vo);
	}

	@Override
	public int noticeModify(noticeVO vo) {
		
		return noticeMapper.noticeModify(vo);
	}

	@Override
	public void noticeDelete(int notice_proper_num) {
		noticeMapper.noticeDelete(notice_proper_num);
		
	}

}
