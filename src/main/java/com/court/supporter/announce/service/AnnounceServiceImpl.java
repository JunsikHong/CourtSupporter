package com.court.supporter.announce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;

@Service("announceService")
public class AnnounceServiceImpl implements AnnounceService{


	@Autowired
	private AnnounceMapper announceMapper;

	@Override
	public int announceRegist(TB_002VO vo) { //공고 등록		
		return announceMapper.announceRegist(vo);
	}
	
	/*
	 * @Override public void createAnnouncemen(TB_002VO vo) { //공고등록시 게시 날짜
	 * LocalDateTime announce_first_date = LocalDateTime.now();
	 * vo.setAnnounce_first_date(announce_first_date); }
	 * 
	 * @Override public void updateAnnouncement(TB_002VO vo) { //공고등록 후 수정 날짜
	 * vo.setAnnounce_last_date(LocalDateTime.now()); }
	 */

	
	@Override
	public ArrayList<TB_002VO> announce_getList(@Param("cri")Criteria cri ) { //공고 조회		
		return announceMapper.announce_getList( cri );
	}
	
	@Override
	public int announce_getTotal(Criteria cri ) { //공고 조회	@Param("cri")	
		return announceMapper.announce_getTotal( cri );
	}
	
//	@Override
//	public List<TB_002VO> searchList(String keyword, Criteria cri) { //공고 검색
//		
//		return announceMapper.searchList(keyword, cri);
//	}

	@Override
	public TB_002VO getDetail(int announce_proper_num) { //공고 상세		
		return announceMapper.getDetail(announce_proper_num);
	}

	@Override
	public int announceModify(TB_002VO vo) { //공고 수정	
		return announceMapper.announceModify(vo);
	}

		
	@Override
	public void announceDelete(int announce_proper_num) { //공고 삭제
		announceMapper.announceDelete(announce_proper_num);
	}


//	@Override
//	public int getTotalByKeyword(String writer, String keyword) {
//		
//		return announceMapper.getTotalByKeyword(writer, keyword);
//	}

	
	
	
}
