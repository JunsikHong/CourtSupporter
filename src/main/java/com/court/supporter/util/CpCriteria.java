package com.court.supporter.util;

import com.court.supporter.util.CpCriteria;

import lombok.Data;

@Data // getter, setter, toString
public class CpCriteria {

	private int page; //조회하는 페이지
	private int amount; //데이터 개수
	
	//검색에 필요한 키워드를 선언
	private String searchLocation;
	private String searchFaq;
	private String searchNotice;

	
	//기본 생성자로 만들어지면 1, 10이 기본값이다.
	public CpCriteria() {
		this.page = 1;
		this.amount = 8;
	}
	
	//생성자에 매개변수가 전달되면 멤버변수를 초기화 함
	public CpCriteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	//페이지 시작값을 지정하는 getter
	public int getPageStart() {
		return (page -1) * amount;
	}
}