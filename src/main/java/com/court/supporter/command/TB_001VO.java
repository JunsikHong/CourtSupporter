package com.court.supporter.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TB_001VO {

	private String user_id;
	private Integer user_proper_num;
	private String user_pw;
	private String user_name;
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
}
