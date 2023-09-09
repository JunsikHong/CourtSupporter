
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
public class TB_001VO {

	private String user_id;
	private String user_proper_num;
	private String user_pw;
	private String user_name;
	private String user_rrn;
	private String user_birthdate;
	private String user_email;
	private String user_phone;
	private String user_job;
	private String user_ar;
	private String user_ar_zonecode;
	private String user_ar_jibun;
	private String user_ar_detail;
	private String user_bank;
	private String user_bank_account;
	private String user_bank_holder;
	private LocalDateTime user_joindate;
	private String user_delete_yn;
	//기본 정보 페이지 핸드폰 번호 분리
	private String user_phone1;
	private String user_phone2;
	private String user_phone3;
	//기본 정보 페이지 이메일 분리
	private String user_email1;
	private String user_email2;
}

