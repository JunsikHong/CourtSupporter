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
public class TB_006VO {

	private Integer edctn_dtls_proper_num;
	private String edctn_school_name;
	private String edctn_major;
	private String edctn_degree;
	private String edctn_admsn_date;
	private String edctn_grdtn_date;
	private String edctn_final_yn;
	private Integer aplcn_dtls_proper_num;
	private String user_id;
	
			
}
