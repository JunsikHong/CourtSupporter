
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

	private String evaluate_proper_num;
	private Integer all_career_score;
	private Integer jrsdc_career_score;
	private Integer office_score;
	private Integer certificate_score;
	private Integer adjust_score;
	private Integer evaluate_score;
	private String review_etc;
	private String admin_proper_num; 
	private String aplcn_dtls_proper_num;
	private String user_id;
	private String user_proper_num;
	
}

