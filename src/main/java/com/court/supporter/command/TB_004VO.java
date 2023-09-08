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
public class TB_004VO {
	//공지사항 테이블
	private int faq_proper_num;
	private String faq_ask_content; 
	private String faq_ask_comment;
	private String faq_ask_date;
	private int admin_proper_num;
}
