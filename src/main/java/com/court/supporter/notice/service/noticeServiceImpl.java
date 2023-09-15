package com.court.supporter.notice.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_016VO;
import com.court.supporter.command.TB_003VO;
import com.court.supporter.util.Criteria;

@Service("noticeService")
public class noticeServiceImpl implements noticeService {

	@Autowired
	private noticeMapper noticeMapper;

	public int noticeRegist(TB_003VO vo, List<String> filelist) {

		// sql 처리
		int result = noticeMapper.noticeRegist(vo);
		String noticepropernum = noticeMapper.getnoticepropernum(vo);
		TB_016VO tb_016vo = new TB_016VO();

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
				String notice_file_uuid = matcher2.group(1);
				String original_file_name = matcher3.group(1);

				tb_016vo.setFile_path(file_path);
				//tb_016vo.setFile_type(file_type);
				tb_016vo.setNotice_file_uuid(notice_file_uuid);
				tb_016vo.setOriginal_file_name(original_file_name);
				tb_016vo.setNotice_proper_num(noticepropernum);

				System.out.println("file_path : " + file_path + " original_file_name : " + original_file_name
						+ " notice_file_uuid : " + notice_file_uuid + " notice_proper_num : " + noticepropernum);
			}

			noticeMapper.noticeFileRegist(tb_016vo);
		}

		return result;
	}

	@Override
	public ArrayList<TB_003VO> noticeList(String writer, Criteria cri) {

		return noticeMapper.noticeList(writer, cri);
	}

	@Override
	public int getTotal(String writer, Criteria cri) {

		return noticeMapper.getTotal(writer, cri);
	}

	@Override
	public TB_003VO noticeDetail(String notice_proper_num) {

		return noticeMapper.noticeDetail(notice_proper_num);
	}

	@Override
	public List<TB_016VO> noticeFileDetail(String notice_proper_num) {
		System.out.println("서비스임플");
		return noticeMapper.noticeFileDetail(notice_proper_num);
	}

	@Override
	public TB_003VO noticeGetNext(String notice_proper_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_003VO noticeGetPrev(String notice_proper_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int noticeModify(TB_003VO vo) {

		return noticeMapper.noticeModify(vo);
	}

	@Override
	public int noticeUpdate(TB_003VO vo, List<String> filelist) {

		// sql 처리
		int result = noticeMapper.noticeUpdate(vo);
		TB_016VO tb_016vo = new TB_016VO();

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
				String notice_file_uuid = matcher2.group(1);
				String original_file_name = matcher3.group(1);

				tb_016vo.setFile_path(file_path);
				//tb_016vo.setFile_type(file_type);
				tb_016vo.setNotice_file_uuid(notice_file_uuid);
				tb_016vo.setOriginal_file_name(original_file_name);
				tb_016vo.setNotice_proper_num(vo.getNotice_proper_num());

				System.out.println("file_path : " + file_path + " original_file_name : " + original_file_name
						+ " notice_file_uuid : " + notice_file_uuid + " notice_proper_num : " + vo.getNotice_proper_num());
			}

			noticeMapper.noticeFileRegist(tb_016vo);
		}
		return result;
	}

	@Override
	public void noticeDelete(String notice_proper_num) {
		noticeMapper.noticeDelete(notice_proper_num);

	}

}
