
package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_014VO {

	private String accept_proper_num;
	private String accept_date;
	private String accept_etc;
	private String accept_act_yn;
	private Integer court_proper_num;
	private String trial_fcltt_proper_num;
	private String user_proper_num;
}

