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
public class faqVO {

	private int faq_proper_num;
	private String faq_ask_content; 
	private String faq_ask_comment;
	private LocalDateTime faq_ask_date;
	private int admin_proper_num;
}
