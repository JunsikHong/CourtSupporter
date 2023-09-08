package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_016VO {
	//공지사항 업로드 테이블
	private Integer notice_file_proper_num;//pk 공지 첨부서류 고유 번호
	private String file_type;//파일타입
	private String file_path;//파일 저장 경로
	private String notice_file_uuid;//UUID
	private String original_file_name;//원본파일이름
	private Integer notice_proper_num;//공지 고유 번호
}
