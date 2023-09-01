package com.court.supporter.command;

import java.time.LocalDateTime;

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
	private Integer upload_no;//pk
	private String filename;//실제파일명
	private String filepath;//폴더명
	private String uuid;//난수값
	private LocalDateTime regdate;
	private Integer id;//fk
	private Integer writer;//fk
}
