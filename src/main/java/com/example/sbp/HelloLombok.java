package com.example.sbp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//@Setter
@RequiredArgsConstructor 
public class HelloLombok {

	private final String hello;
	
	private final  int lombok;
	
	public static void main(String[] args) {
		HelloLombok hl = new HelloLombok("안녕",200);
		
		System.out.println(hl.getHello());
		System.out.println(hl.getLombok());
	}
}
