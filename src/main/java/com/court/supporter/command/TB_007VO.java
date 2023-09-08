package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_007VO {

	private Integer aplcn_carer_proper_num;
	private String company_name;
	private String carer_type;
	private String work_start_date;
	private String work_end_date;
	private String work_description;
	private String work_department;
	private String work_position;
	private String carer_description;
	private String special_note_description;
	private Integer aplcn_dtls_proper_num;
	private String user_id;
	
}
