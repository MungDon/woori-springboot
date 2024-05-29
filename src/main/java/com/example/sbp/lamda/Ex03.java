package com.example.sbp.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ex03 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("java","jsp","html","css","spring","java");
		
		// 리스트로부터 스트림 생성
		Stream<String> st = list.stream();
		st = st.distinct(); // 중복된 값 제거
	
		// 반복으로 출력
		st.forEach(n -> System.out.println(n));
	
		System.out.println("================");
		
		list.stream()
			 .distinct()
			 .forEach(n->System.out.println(n));
		
		System.out.println("================");
		
		
		list.stream()
			 .filter(n -> n.startsWith("j"))
			 .forEach(n -> System.out.println(n));
	}
}
