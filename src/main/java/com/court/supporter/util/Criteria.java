package com.court.supporter.util;

import com.court.supporter.util.Criteria;

import lombok.Data;

@Data // getter, setter, toString
public class Criteria {

	private int page; //조회하는 페이지
	private int amount; //데이터 개수
	

	private String announce_title;
	private String announce_content;
	private String announce_start_date;
	private String announce_end_date;
	private String aplicn_dtls_sts;
	private String aplicn_dtls_startdate;
	private String aplicn_dtls_enddate;
	private String searchFaq;
	private String searchNotice;

	
	//기본 생성자로 만들어지면 1, 10이 기본값이다.
	public Criteria() {
		this.page = 1;
		this.amount = 8;
	}
	
	//생성자에 매개변수가 전달되면 멤버변수를 초기화 함
	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	//페이지 시작값을 지정하는 getter
	public int getPageStart() {
		return (page -1) * amount;
	}
}