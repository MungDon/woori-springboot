package com.example.sbp.lamda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Ex02 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("aa","bb","cc");
		
		// Iterator 방식
		Iterator<String> iter = list.iterator();
		
		while(iter.hasNext()) {
			String abc = iter.next();
			System.out.println(abc); 
		}
		
		// 또 다른 List
		List<String> list2 = Arrays.asList("100","200","300");
		// 서터림
		Stream<String> stream = list2.stream();
		// 서터림 사용하여 값 꺼내줌
		stream.forEach(ott -> System.out.println(ott));
	}
}
