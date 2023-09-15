package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_010VO {

	private String trial_fcltt_proper_num;
	private String trial_fcltt_mainclasifi_code;
	private String trial_fcltt_clasifi_code;
	private String trial_fcltt_sbcls_code;
	private String trial_fcltt_description;
	
}
