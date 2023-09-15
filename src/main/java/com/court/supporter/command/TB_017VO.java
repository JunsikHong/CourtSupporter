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
public class TB_017VO {	
	
		private Integer announce_file_proper_num;
		private String file_type;
		private String original_file_name;
		private String file_path;		
		private String announce_proper_num;
		private String announce_file_uuid;
}
