package com.court.supporter.command;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_005VO {

	private Integer aplcn_dtls_proper_num;
	private String ligtn_case_carer_yn;
	private String ligtn_case_carer_etc;
	private String insrn_indst_carer_yn;
	private String insrn_indst_carer_etc;
	private String criminal_penalty_carer_yn;
	private String criminal_penalty_carer_etc;
	private String aplicn_dtls_sts;
	private LocalDateTime aplicn_dtls_date;
	private Integer user_proper_num;
	private Integer trial_fcltt_proper_num;
	private Integer court_proper_num;
	private String user_id;
	//멀티셀렉트 값
	private List<Integer> court_proper_num_list;
}
