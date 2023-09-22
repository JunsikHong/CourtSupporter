
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
	private Double all_career_score;
	private Double jrsdc_career_score;
	private Double office_score;
	private Double certificate_score;
	private Double adjust_score;
	private Double evaluate_score;
	private String review_etc;
	private String admin_proper_num; 
	private String aplcn_dtls_proper_num;
	private String user_id;
	private String user_proper_num;
	
	private String member_id;
}

