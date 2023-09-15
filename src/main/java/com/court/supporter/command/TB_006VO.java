package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_006VO {

	private String edctn_dtls_proper_num;
	private String edctn_school_name;
	private String edctn_major;
	private String edctn_degree;
	private String edctn_admsn_date;
	private String edctn_grdtn_date;
	private String edctn_final_yn;
	private String aplcn_dtls_proper_num;
	private String user_id;
	private String user_proper_num;

	//최종학력 체크
	private String final_education_chk;
}
