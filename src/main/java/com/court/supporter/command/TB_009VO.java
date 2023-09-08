package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_009VO {

	private Integer aplcn_atch_file_proper_num;
	private String file_code;
	private String file_type;
	private String original_file_name;
	private String uuid;
	private String file_path;
	private Integer aplcn_dtls_proper_num;
	private String user_id;
	
}
