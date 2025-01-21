package com.marondal.marondalgram.common.dto;

import lombok.Getter;

@Getter
public class Response<T> {
	
//	result	String	"API 수행 결과
//	success : 성공
//	fail : 실패"
//	data	T	추가 응답 값
	
	private String result;
	private T data;
	
	public Response(String result, T data) {
		this.result = result;
		this.data = data;
	}
	

}
