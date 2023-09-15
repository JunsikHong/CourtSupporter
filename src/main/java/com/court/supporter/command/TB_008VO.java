package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_008VO {

	private String aplcn_crtfc_proper_num;
	private String crtfc_type;
	private String issue_agency;
	private String crtfc_number;
	private String issue_date;
	private String aplcn_dtls_proper_num;
	private String user_id;
	private String user_proper_num;

}
