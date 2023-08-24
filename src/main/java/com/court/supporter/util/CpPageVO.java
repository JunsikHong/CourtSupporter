package com.court.supporter.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data // getter, setter, toString
public class CpPageVO {

	private int start; //시작페이지네이션 (한번에 보여지는 목록 중)
	private int end; //끝페이지네이션 (한번에 보여지는 목록 중)
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	private int realEnd; //실제 보여지는 끝번호
	
	private CpCriteria cri; //페이지기준
	private int page; //cri에 있는 현재조회하는 페이지
	private int amount; //cri에 있는 데이터 개수
	private int total; //전체게시글 개수
	
	private int pnCount = 5; //페이지네이션 개수
	private List<Integer> pageList; //페이지네이션을 리스트로 저장
	
	//페이지네이션 클래스는 cri와 total을 매개변수로 받는다.
	public CpPageVO(CpCriteria cri, int total) {
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		
		//start, end페이지 계산
		//page가 1~10을 가리킬 때, start=1 end=10
		//page가 11~20을 가리킬 때, start=11 end=20
		
		//this.end(끝 페이지네이션) = (int)(Math.ceil(현재 조회하는 페이지 / 페이지네이션 개수)) * 페이지네이션 개수;
		this.end = (int)(Math.ceil(this.page / (double)pnCount)) * this.pnCount;
		
		//this.start(시작 페이지네이션) = 페이지네이션 개수 + 1;
		this.start = this.end - this.pnCount + 1;
		
		//실제 끝번호의 계산 -> 데이터 10개씩 보여준다고 했을 때
		//총게시글 수가 53개 -> 마지막 번호는 6
		//총게시글 수가 232개 -> 마지막 번호는 24
		//this.realEnd = (int)(Math.ceil(전체게시글 수 / (double)데이터개수));
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
		
		//end페이지 계산
		//데이터개수 25개 -> end=10, realEnd=3 (실제 끝번호를 따라감)
		//데이터개수 153개 -> end=20, realEnd=16 (실제 끝번호를 따라감)
		//데이터개수 153개(3번페이지 조회시) -> end=10, realEnd=16 페이지네이션을 따라간다.)
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//prev 활성화여부
		//맨앞에선 없앤다.
		//start페이지의 결정은 1, 11, 21, 31 ...
		this.prev = this.start > 1;
		
		//next 활성화여부
		//end=10, realEnd=16 이라고 할 때 다음버튼이 있다는 의미
		this.next = this.realEnd > this.end;
		
		//타임리프 - 리스트에 페이지네이션 담음
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	}
}