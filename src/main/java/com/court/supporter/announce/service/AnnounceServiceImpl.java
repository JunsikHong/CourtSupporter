package com.court.supporter.announce.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_017VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;


@Service("announceService")
public class AnnounceServiceImpl implements AnnounceService{


	@Autowired
	private AnnounceMapper announceMapper;

	@Override
	public int announceRegist(TB_002VO vo, List<String> filelist) { //공고 등록	, List<MultipartFile> list	

		// sql 처리
	      int result = announceMapper.announceRegist(vo);

	      TB_017VO fileVO = new TB_017VO();

	      for (String filePath : filelist) {

	         String regex1 = "(.*?/.*?/)"; // 파일 저장 경로
	         String regex2 = "([^/]+)_"; // UUID
	         // String regex3 = "_(.*?)\\.[^.]+$"; //원본 파일 이름
	         String regex3 = "_(.*)"; // 원본 파일 이름

	         Pattern pattern1 = Pattern.compile(regex1); // 파일 저장 경로
	         Pattern pattern2 = Pattern.compile(regex2); // UUID
	         Pattern pattern3 = Pattern.compile(regex3); // 원본 파일 이름

	         Matcher matcher1 = pattern1.matcher(filePath);
	         Matcher matcher2 = pattern2.matcher(filePath);
	         Matcher matcher3 = pattern3.matcher(filePath);

	         if (matcher1.find() && matcher2.find() && matcher3.find()) {

	            String file_path = matcher1.group(1);
	            String announce_file_uuid = matcher2.group(1);
	            String original_file_name = matcher3.group(1);

	            fileVO.setFile_path(file_path);
	            //tb_016vo.setFile_type(file_type);
	            fileVO.setAnnounce_file_uuid(announce_file_uuid);
	            fileVO.setOriginal_file_name(original_file_name);
	            fileVO.setAnnounce_proper_num(vo.getAnnounce_proper_num());

	            System.out.println("file_path : " + file_path + " original_file_name : " + original_file_name
	                  + " announce_file_uuid : " + announce_file_uuid + " announce_proper_num : " + vo.getAnnounce_proper_num());
	         }

	         announceMapper.announceFileRegist(fileVO);
	      }

	      return result;
	   }
	
			
	@Override
	public ArrayList<TB_002VO> announce_getList(@Param("cri")Criteria cri ) { //공고 조회		
		return announceMapper.announce_getList( cri );
	}
	
	@Override
	public int announce_getTotal(Criteria cri ) { //공고 조회	@Param("cri")	
		return announceMapper.announce_getTotal( cri );
	}
	
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

	@Override
	public TB_002VO getPrev(int announce_proper_num) {
		return announceMapper.getPrev(announce_proper_num);		
	}

	@Override
	public TB_002VO getNext(int announce_proper_num) {
		return announceMapper.getNext(announce_proper_num);	
	}

	@Override
	public List<TB_017VO> getFileDetail(int announce_proper_num) {
		
		return announceMapper.getFileDetail(announce_proper_num);
	}

	
}
