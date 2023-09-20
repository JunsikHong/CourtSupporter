package com.court.supporter.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_019VO {
	//사용자 이미지 업로드 테이블
	private String img_file_proper_num;//pk 이미지 고유 번호
	private String file_type;//파일타입
	private String file_path;//파일 저장 경로
	private String img_file_uuid;//UUID
	private String original_file_name;//원본파일이름
	private String user_proper_num;//사용자 고유 번호
}
