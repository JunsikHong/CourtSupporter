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
public class TB_012VO {

	private Integer act_proper_num;
	private Integer judgement_num;
	private String chosen_date;
	private String attendance_date;
	private String act_complete_yn;
	private Integer aplcn_carer_proper_num;
	private String user_id;
	
}
