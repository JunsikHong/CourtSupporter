package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_013VO {

	private Integer evaluate_proper_num;
	private Integer all_career_score;
	private Integer jrsdc_career_score;
	private Integer offic_score;
	private Integer personality_score;
	private Integer interview_score;
	private Integer certificate_score;
	private Integer evaluate_score;
	private Integer judge_recom_score;
	private String review_etc;
	private Integer admin_proper_num; 
	private Integer aplcn_dtls_proper_num;
	private String user_id;
	
}
