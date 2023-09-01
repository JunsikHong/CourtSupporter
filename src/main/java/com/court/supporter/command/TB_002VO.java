
package com.court.supporter.command;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TB_002VO {

	private Integer announce_proper_num; //공고고유번호
	private String announce_title; //공고 제목
	private String announce_content;	//공고 내용	
	
	private String announce_start_date;	//게시 시작일자
	private String announce_end_date;	//게시 종료일자
	
	private String announce_first_date;	//최초입력일시
	private String announce_last_date;	//최종변경일시
	private Integer admin_proper_num;	//관리자 계정 고유번호
	private Integer trial_fcltt_proper_num; //재판조력자 고유번호
	
}
