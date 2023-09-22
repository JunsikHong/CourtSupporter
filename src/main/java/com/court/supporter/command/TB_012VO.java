
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

	private String act_proper_num;
	private String judgement_num;
	private String chosen_date;
	private String attendance_date;
	private String act_complete_yn;
	private String act_etc;
	private String user_id;
	private String user_proper_num;
	
}

